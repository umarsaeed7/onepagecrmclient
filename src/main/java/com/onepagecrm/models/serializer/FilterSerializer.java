package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Filter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 01/12/2015.
 */
public class FilterSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(FilterSerializer.class.getName());

    public static List<Filter> fromJsonArray(JSONArray filtersArray) {
        List<Filter> filters = new ArrayList<>();
        for (int j = 0; j < filtersArray.length(); j++) {
            try {
                JSONObject filterOuterObject = filtersArray.getJSONObject(j);
                JSONObject filterObject = filterOuterObject.getJSONObject(FILTER_TAG);
                filters.add(fromJsonObject(filterObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing Tag array");
                LOG.severe(e.toString());
            }
        }
        return filters;
    }

    public static Filter fromJsonObject(JSONObject filterObject) {
        Filter filter = new Filter();
        try {
            if (filterObject.has(ID_TAG)) {
                filter.setId(filterObject.getString(ID_TAG));
            }
            if (filterObject.has(NAME_TAG)) {
                filter.setName(filterObject.getString(NAME_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Filter object");
            LOG.severe(e.toString());
        }
        return filter;
    }

    public static String toJsonObject(Filter filter) {
        if (filter.getId() != null && filter.getName() != null) {
            JSONObject tagObject = new JSONObject();
            addJsonStringValue(filter.getId(), tagObject, ID_TAG);
            addJsonStringValue(filter.getName(), tagObject, NAME_TAG);
            return tagObject.toString();
        }
        return null;
    }

    public static String toJsonArray(List<Filter> filters) {
        JSONArray filtersArray = new JSONArray();
        if (filters != null && !filters.isEmpty()) {
            for (int i = 0; i < filters.size(); i++) {
                try {
                    String filterString = toJsonObject(filters.get(i));
                    if (filterString != null) {
                        JSONObject filterDetailsObject = new JSONObject(filterString);
                        JSONObject filterObject = new JSONObject();
                        addJsonObject(filterDetailsObject, filterObject, FILTER_TAG);
                        addJsonObject(filterObject, filtersArray);
                    }
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of Tags");
                    LOG.severe(e.toString());
                }
            }
        }
        return filtersArray.toString();
    }
}
