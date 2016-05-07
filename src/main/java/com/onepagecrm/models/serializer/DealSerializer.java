package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Deal;
import com.onepagecrm.models.DealList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(DealSerializer.class.getName());

    public static Deal fromString(String responseBody) throws OnePageException {
        Deal deal = new Deal();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);

            deal = fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing Deal object from response body");
            LOG.severe(e.toString());
        }
        return deal;
    }

    public static Deal fromJsonObject(JSONObject dealsObject) {
        Deal deal = new Deal();
        try {
            JSONObject dealObject = dealsObject.getJSONObject(DEAL_TAG);
            if (dealObject.has(ID_TAG)) {
                deal.setId(dealObject.getString(ID_TAG));
            }
            if (dealObject.has(AMOUNT_TAG)) {
                deal.setAmount(dealObject.getDouble(AMOUNT_TAG));
            }
            if (dealObject.has(AUTHOR_TAG)) {
                deal.setAuthor(dealObject.getString(AUTHOR_TAG));
            }
            if (dealObject.has(TEXT_TAG)) {
                deal.setText(dealObject.getString(TEXT_TAG));
            }
            if (dealObject.has(CONTACT_ID_TAG)) {
                deal.setContactId(dealObject.getString(CONTACT_ID_TAG));
            }
            if (dealObject.has(CREATED_AT_TAG)) {
                String createdAtStr = dealObject.getString(CREATED_AT_TAG);
                Date createdAt = DateSerializer.fromFormattedString(createdAtStr);
                deal.setCreatedAt(createdAt);
            }
            if (dealObject.has(DATE_TAG)) {
                String dateStr = dealObject.getString(DATE_TAG);
                Date date = DateSerializer.fromFormattedString(dateStr);
                deal.setDate(date);
            }
            if (dealObject.has(EXPECTED_CLOSE_DATE_TAG)) {
                String expectedCloseDateStr = dealObject.getString(EXPECTED_CLOSE_DATE_TAG);
                Date expectedCloseDate = DateSerializer.fromFormattedString(expectedCloseDateStr);
                deal.setExpectedCloseDate(expectedCloseDate);
            }
            if (dealObject.has(MONTHS_TAG)) {
                deal.setMonths(dealObject.getInt(MONTHS_TAG));
            }
            if (dealObject.has(NAME_TAG)) {
                deal.setName(dealObject.getString(NAME_TAG));
            }
            if (dealObject.has(OWNER_ID_TAG)) {
                deal.setOwnerId(dealObject.getString(OWNER_ID_TAG));
            }
            if (dealObject.has(STAGE_TAG)) {
                deal.setStage(dealObject.getInt(STAGE_TAG));
            }
            if (dealObject.has(STATUS_TAG)) {
                deal.setStatus(dealObject.getString(STATUS_TAG));
            }
            if (dealObject.has(TOTAL_AMOUNT_TAG)) {
                deal.setTotalAmount(dealObject.getDouble(TOTAL_AMOUNT_TAG));
            }
            if (dealObject.has(MODIFIED_AT_TAG)) {
                String modifiedAtStr = dealObject.getString(MODIFIED_AT_TAG);
                Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtStr);
                deal.setModifiedAt(modifiedAt);
            }
            if (dealObject.has(HAS_RELATED_NOTES_TAG)) {
                deal.setHasRelatedNotes(dealObject.getBoolean(HAS_RELATED_NOTES_TAG));
            }
            if (dealObject.has(CLOSE_DATE_TAG)) {
                if (!dealObject.isNull(CLOSE_DATE_TAG)) {
                    String closeDateStr = dealObject.getString(CLOSE_DATE_TAG);
                    Date closeDate = DateSerializer.fromFormattedString(closeDateStr);
                    deal.setCloseDate(closeDate);
                }
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Deal object");
            LOG.severe(e.toString());
        }
        return deal;
    }

    public static String toJsonObject(Deal deal) {
        JSONObject dealObject = new JSONObject();
        addJsonStringValue(deal.getId(), dealObject, ID_TAG);
        addJsonDoubleValue(deal.getAmount(), dealObject, AMOUNT_TAG);
        addJsonStringValue(deal.getAuthor(), dealObject, AUTHOR_TAG);
        addJsonStringValue(deal.getText(), dealObject, TEXT_TAG);
        addJsonStringValue(deal.getContactId(), dealObject, CONTACT_ID_TAG);
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(deal.getCreatedAt()),
                dealObject,
                CREATED_AT_TAG
        );
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(deal.getDate()),
                dealObject,
                DATE_TAG
        );
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(deal.getExpectedCloseDate()),
                dealObject,
                EXPECTED_CLOSE_DATE_TAG
        );
        addJsonIntegerValue(deal.getMonths(), dealObject, MONTHS_TAG);
        addJsonStringValue(deal.getName(), dealObject, NAME_TAG);
        addJsonStringValue(deal.getOwnerId(), dealObject, OWNER_ID_TAG);
        addJsonIntegerValue(deal.getStage(), dealObject, STAGE_TAG);
        addJsonStringValue(deal.getStatus(), dealObject, STATUS_TAG);
        addJsonDoubleValue(deal.getTotalAmount(), dealObject, TOTAL_AMOUNT_TAG);
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(deal.getModifiedAt()),
                dealObject,
                MODIFIED_AT_TAG
        );
        addJsonBooleanValue(deal.getHasRelatedNotes(), dealObject, HAS_RELATED_NOTES_TAG);
        addJsonStringValue(
                DateSerializer.toFormattedDateTimeString(deal.getCloseDate()),
                dealObject,
                CLOSE_DATE_TAG
        );
        return dealObject.toString();
    }

    public static String toJsonArray(DealList deals) {
        JSONArray dealsArray = new JSONArray();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                try {
                    dealsArray.put(new JSONObject(toJsonObject(deals.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of Deals");
                    LOG.severe(e.toString());
                }
            }
        }
        return dealsArray.toString();
    }
}
