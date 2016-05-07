package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class LoginSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(LoginSerializer.class.getName());

    public static User fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            Account.loggedInUser = UserSerializer.fromString(parsedResponse);
            addTagsToAccount(responseBody);
            addStatusesToAccount(responseBody);
            addLeadSourcesToAccount(responseBody);
            addFiltersToAccount(responseBody);
            return Account.loggedInUser;

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;
        }
    }

    public static String toJsonObject(String username, String password) {
        JSONObject loginObject = new JSONObject();
        try {
            loginObject.put(LOGIN_TAG, username);
            loginObject.put(PASSWORD_TAG, password);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONObject from login values");
            LOG.severe(e.toString());
        }
        return loginObject.toString();
    }

    private static void addTagsToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(TAGS_TAG)) {
                addTags(responseObject.getJSONObject(TAGS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Tags array");
            LOG.severe(e.toString());
        }
    }

    private static void addTags(JSONObject tagsObject) throws JSONException {
        List<Tag> tags = TagSerializer.fromJsonArray(
                tagsObject.getJSONArray(TAGS_TAG)
        );
        Account.loggedInUser.getAccount().setTags(tags);
    }

    private static void addStatusesToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(STATUSES_TAG)) {
                addStatuses(responseObject.getJSONArray(STATUSES_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Status array");
            LOG.severe(e.toString());
        }
    }

    private static void addStatuses(JSONArray statusesArray) throws JSONException {
        List<Status> statuses = StatusSerializer.fromJsonArray(statusesArray);
        Account.loggedInUser.getAccount().setStatuses(statuses);
    }

    private static void addLeadSourcesToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            if (responseObject.has(LEAD_SOURCES_TAG)) {
                addLeadSources(responseObject.getJSONArray(LEAD_SOURCES_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing LeadSource array");
            LOG.severe(e.toString());
        }
    }

    private static void addLeadSources(JSONArray leadSourceArray) throws JSONException {
        List<LeadSource> leadSources = LeadSourceSerializer.fromJsonArray(leadSourceArray);
        Account.loggedInUser.getAccount().setLeadSources(leadSources);
    }

    private static void addFiltersToAccount(String responseBody) {
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
            if (dataObject.has(FILTERS_TAG)) {
                addFilters(dataObject.getJSONArray(FILTERS_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Filters array");
            LOG.severe(e.toString());
        }
    }

    private static void addFilters(JSONArray filterArray) throws JSONException {
        List<Filter> filters = FilterSerializer.fromJsonArray(filterArray);
        Account.loggedInUser.getAccount().setFilters(filters);
    }
}
