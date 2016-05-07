package com.onepagecrm.models.serializer;

import com.onepagecrm.models.internal.TeamCount;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TeamCountsSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(TeamCountsSerializer.class.getName());

    public static List<TeamCount> fromJsonArray(JSONArray teamCountArray) {
        List<TeamCount> teamCounts = new ArrayList<>();
        for (int j = 0; j < teamCountArray.length(); j++) {
            JSONObject teamCountsObject;
            try {
                teamCountsObject = teamCountArray.getJSONObject(j);
                teamCounts.add(fromJsonObject(teamCountsObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing TeamCount array");
                LOG.severe(e.toString());
            }
        }
        return teamCounts;
    }

    public static TeamCount fromJsonObject(JSONObject teamCountObject) {
        TeamCount teamCount = new TeamCount();
        try {
            if (teamCountObject.has(USER_ID_TAG)) {
                teamCount.setUserId(teamCountObject.getString(USER_ID_TAG));
            }
            if (teamCountObject.has(COUNTS_TAG)) {
                teamCount.setCounts(teamCountObject.getInt(COUNTS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing TeamCount object");
            LOG.severe(e.toString());
        }
        return teamCount;
    }

    public static String toJsonObject(TeamCount teamCount) {
        JSONObject teamCountObject = new JSONObject();
        addJsonStringValue(teamCount.getUserId(), teamCountObject, USER_ID_TAG);
        addJsonIntValue(teamCount.getCounts(), teamCountObject, COUNTS_TAG);
        return teamCountObject.toString();
    }

    public static String toJsonArray(List<TeamCount> teamCounts) {
        JSONArray teamCountsArray = new JSONArray();
        if (teamCounts != null && !teamCounts.isEmpty()) {
            for (int i = 0; i < teamCounts.size(); i++) {
                try {
                    teamCountsArray.put(new JSONObject(toJsonObject(teamCounts.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of TeamCounts");
                    LOG.severe(e.toString());
                }
            }
        }
        return teamCountsArray.toString();
    }
}
