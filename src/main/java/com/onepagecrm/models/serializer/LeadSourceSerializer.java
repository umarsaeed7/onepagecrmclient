package com.onepagecrm.models.serializer;

import com.onepagecrm.models.LeadSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LeadSourceSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LeadSourceSerializer.class.getName());

    public static List<LeadSource> fromJsonArray(JSONArray leadSourceArray) {
        List<LeadSource> leadSources = new ArrayList<>();
        for (int j = 0; j < leadSourceArray.length(); j++) {
            JSONObject leadSourceObject;
            try {
                leadSourceObject = leadSourceArray.getJSONObject(j);
                leadSources.add(fromJsonObject(leadSourceObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing LeadSource array");
                LOG.severe(e.toString());
            }
        }
        return leadSources;
    }

    public static LeadSource fromJsonObject(JSONObject leadSourceObject) {
        LeadSource leadSource = new LeadSource();
        try {

            if (leadSourceObject.has(COUNTS_TAG)) {
                leadSource.setCounts(leadSourceObject.getInt(COUNTS_TAG));
            }
            if (leadSourceObject.has(TOTAL_COUNT_TAG)) {
                leadSource.setTotalCount(leadSourceObject.getInt(TOTAL_COUNT_TAG));
            }
            if (leadSourceObject.has(TEAM_COUNTS_TAG)) {
                leadSource.setTeamCounts(TeamCountsSerializer.fromJsonArray(leadSourceObject.getJSONArray(TEAM_COUNTS_TAG)));
            }
            if (leadSourceObject.has(ID_TAG)) {
                leadSource.setId(leadSourceObject.getString(ID_TAG));
            }
            if (leadSourceObject.has(TEXT_TAG)) {
                leadSource.setText(leadSourceObject.getString(TEXT_TAG));
            }
            return leadSource;
        } catch (JSONException e) {
            LOG.severe("Error parsing LeadSource object");
            LOG.severe(e.toString());
        }
        return leadSource;
    }

    public static String toJsonObject(LeadSource leadSource) {
        JSONObject leadSourceObject = new JSONObject();
        addJsonIntValue(leadSource.getCounts(), leadSourceObject, COUNTS_TAG);
        addJsonIntValue(leadSource.getTotalCount(), leadSourceObject, TOTAL_COUNT_TAG);

        try {
            JSONArray teamCountsArray = new JSONArray(TeamCountsSerializer.toJsonArray(leadSource.getTeamCounts()));
            addJsonArray(teamCountsArray, leadSourceObject, TEAM_COUNTS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating TeamCounts array while constructing LeadSource object");
            LOG.severe(e.toString());
        }

        addJsonStringValue(leadSource.getId(), leadSourceObject, ID_TAG);
        addJsonStringValue(leadSource.getText(), leadSourceObject, TEXT_TAG);
        return leadSourceObject.toString();
    }

    public static String toJsonArray(List<LeadSource> leadSources) {
        JSONArray leadSourceArray = new JSONArray();
        if (leadSources != null && !leadSources.isEmpty()) {
            for (int i = 0; i < leadSources.size(); i++) {
                try {
                    leadSourceArray.put(new JSONObject(toJsonObject(leadSources.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of LeadSource");
                    LOG.severe(e.toString());
                }
            }
        }
        return leadSourceArray.toString();
    }
}
