package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatusSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(StatusSerializer.class.getName());

    public static List<Status> fromJsonArray(JSONArray statusArray) {
        List<Status> statuses = new ArrayList<>();
        for (int j = 0; j < statusArray.length(); j++) {
            JSONObject statusObject;
            try {
                statusObject = statusArray.getJSONObject(j);
                statusObject = statusObject.getJSONObject(STATUS_TAG);
                statuses.add(fromJsonObject(statusObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing Status array");
                LOG.severe(e.toString());
            }
        }
        return statuses;
    }

    public static Status fromJsonObject(JSONObject statusObject) {
        Status status = new Status();
        try {
            if (statusObject.has(COUNTS_TAG)) {
                status.setCounts(statusObject.getInt(COUNTS_TAG));
            }
            if (statusObject.has(TOTAL_COUNT_TAG)) {
                status.setTotalCount(statusObject.getInt(TOTAL_COUNT_TAG));
            }
            if (statusObject.has(TEAM_COUNTS_TAG)) {
                status.setTeamCounts(TeamCountsSerializer.fromJsonArray(statusObject.getJSONArray(TEAM_COUNTS_TAG)));
            }
            if (statusObject.has(ID_TAG)) {
                status.setId(statusObject.getString(ID_TAG));
            }
            if (statusObject.has(COLOR_TAG)) {
                status.setColor(statusObject.getString(COLOR_TAG));
            }
            if (statusObject.has(STATUS_TAG)) {
                status.setStatus(statusObject.getString(STATUS_TAG));
            }
            if (statusObject.has(TEXT_TAG)) {
                status.setText(statusObject.getString(TEXT_TAG));
            }
            if (statusObject.has(DESCRIPTION_TAG)) {
                status.setDescription(statusObject.getString(DESCRIPTION_TAG));
            }
            if (statusObject.has(ACTION_STREAM_COUNT_TAG)) {
                status.setActionStreamCount(statusObject.getInt(ACTION_STREAM_COUNT_TAG));
            }
            return status;
        } catch (JSONException e) {
            LOG.severe("Error parsing Status object");
            LOG.severe(e.toString());
        }
        return status;
    }

    public static String toJsonObject(Status status) {
        JSONObject statusObject = new JSONObject();
        addJsonIntValue(status.getCounts(), statusObject, COUNTS_TAG);
        addJsonIntValue(status.getTotalCount(), statusObject, TOTAL_COUNT_TAG);

        try {
            JSONArray teamCountsArray = new JSONArray(TeamCountsSerializer.toJsonArray(status.getTeamCounts()));
            addJsonArray(teamCountsArray, statusObject, TEAM_COUNTS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating TeamCounts array while constructing Status object");
            LOG.severe(e.toString());
        }

        addJsonStringValue(status.getId(), statusObject, ID_TAG);
        addJsonStringValue(status.getColor(), statusObject, COLOR_TAG);
        addJsonStringValue(status.getStatus(), statusObject, STATUS_TAG);
        addJsonStringValue(status.getText(), statusObject, TEXT_TAG);
        addJsonStringValue(status.getDescription(), statusObject, DESCRIPTION_TAG);
        addJsonIntValue(status.getActionStreamCount(), statusObject, ACTION_STREAM_COUNT_TAG);
        return statusObject.toString();
    }

    public static String toJsonArray(List<Status> statuses) {
        JSONArray statusArray = new JSONArray();
        if (statuses != null && !statuses.isEmpty()) {
            for (int i = 0; i < statuses.size(); i++) {
                try {
                    statusArray.put(new JSONObject(toJsonObject(statuses.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of Statuses");
                    LOG.severe(e.toString());
                }
            }
        }
        return statusArray.toString();
    }
}
