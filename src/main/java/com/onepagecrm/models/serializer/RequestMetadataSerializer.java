package com.onepagecrm.models.serializer;

import com.onepagecrm.models.internal.Paginator;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 02/12/2015.
 */
public class RequestMetadataSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(RequestMetadataSerializer.class.getName());

    public static Paginator fromJsonObject(JSONObject dataObject) {
        Paginator paginator = new Paginator();
        try {
            if (dataObject.has(TOTAL_COUNT_TAG)) {
                paginator.setTotalCount(dataObject.getInt(TOTAL_COUNT_TAG));
            }
            if (dataObject.has(PAGE_TAG)) {
                paginator.setCurrentPage(dataObject.getInt(PAGE_TAG));
            }
            if (dataObject.has(MAX_PAGE_TAG)) {
                paginator.setMaxPage(dataObject.getInt(MAX_PAGE_TAG));
            }
            if (dataObject.has(PER_PAGE_TAG)) {
                paginator.setPerPage(dataObject.getInt(PER_PAGE_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Deal object");
            LOG.severe(e.toString());
        }
        return paginator;
    }

    public static String toJsonObject(Paginator paginator) {
        JSONObject paginatorObject = new JSONObject();
        addJsonIntegerValue(paginator.getTotalCount(), paginatorObject, TOTAL_COUNT_TAG);
        addJsonIntegerValue(paginator.getCurrentPage(), paginatorObject, PAGE_TAG);
        addJsonIntegerValue(paginator.getMaxPage(), paginatorObject, MAX_PAGE_TAG);
        addJsonIntegerValue(paginator.getPerPage(), paginatorObject, PER_PAGE_TAG);
        return paginatorObject.toString();
    }

    public static String toJsonKeyValuePair(Paginator paginator) {
        String jsonValues = toJsonObject(paginator);
        return jsonValues.substring(1, jsonValues.length() - 2);
    }
}
