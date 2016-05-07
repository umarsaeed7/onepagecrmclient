package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealListSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DealListSerializer.class.getName());

    /**
     * Parse response from deals request to construct Deal object(s).
     *
     * @param responseBody
     * @return
     */
    public static DealList fromString(String responseBody) throws OnePageException {
        DealList deals = new DealList();
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONArray dealsArray = new JSONObject(parsedResponse).getJSONArray(DEALS_TAG);
            deals = fromJsonArray(dealsArray);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Error parsing deals array from response body");
            LOG.severe(e.toString());
        }

        return deals;
    }

    /**
     * Parse response from deals request to construct Deal object(s).
     *
     * @param dealsArray
     * @return
     */
    public static DealList fromJsonArray(JSONArray dealsArray) {
        ArrayList<Deal> deals = new ArrayList<>();
        try {
            for (int i = 0; i < dealsArray.length(); i++) {
                JSONObject dealObject = dealsArray.getJSONObject(i);
                deals.add(DealSerializer.fromJsonObject(dealObject));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing deals array from response body");
            LOG.severe(e.toString());
        }
        return new DealList(deals);
    }
}
