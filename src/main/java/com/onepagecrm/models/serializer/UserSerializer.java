package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Account;
import com.onepagecrm.models.CallResult;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class UserSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactList.class.getName());

    public static User fromString(String dataString) {
        try {
            JSONObject dataObject = new JSONObject(dataString);
            String userId = dataObject.getString(USER_ID_TAG);
            String authKey = dataObject.getString(AUTH_KEY_TAG);
            String accountType = dataObject.getString(ACCOUNT_TYPE_TAG);

            User user = new User()
                    .setId(userId)
                    .setAuthKey(authKey)
                    .setAccountType(accountType);

            user.setAccount(new Account()
                            .setCustomFields(
                                    CustomFieldSerializer.fromJsonArray(dataObject.getJSONArray(CUSTOM_FIELDS_TAG))
                            )
            );

            user = addCallResults(dataObject, user);

            JSONObject outsideUserObject = dataObject.getJSONObject(USER_TAG);
            JSONObject userObject = outsideUserObject.getJSONObject(USER_TAG);
            user = fromJsonObject(userObject, user);

            // Add other team members to Account object
            List<User> team = fromJsonArray(dataObject.getJSONArray(TEAM_TAG));
            // Added logged in User as first in User's list
            team.add(0, user);
            // Set list of Users (team) for this account.
            user.getAccount().setTeam(team);

            return user;

        } catch (JSONException e) {
            LOG.severe("Error parsing user response");
            LOG.severe(e.toString());
            return new User();
        }
    }

    public static User fromJsonObject(JSONObject userObject, User user) {
        try {
            if (userObject.has(ID_TAG)) {
                user.setId(userObject.getString(ID_TAG));
            }
            if (userObject.has(FIRST_NAME_TAG)) {
                user.setFirstName(userObject.getString(FIRST_NAME_TAG));
            }
            if (userObject.has(LAST_NAME_TAG)) {
                user.setLastName(userObject.getString(LAST_NAME_TAG));
            }
            if (userObject.has(EMAIL_TAG)) {
                user.setEmail(userObject.getString(EMAIL_TAG));
            }
            if (userObject.has(COMPANY_NAME_TAG)) {
                user.setCompanyName(userObject.getString(COMPANY_NAME_TAG));
            }
            if (userObject.has(PHOTO_URL_TAG)) {
                user.setPhotoUrl(userObject.getString(PHOTO_URL_TAG));
            }
            if (userObject.has(BCC_EMAIL_TAG)) {
                user.setBccEmail(userObject.getString(BCC_EMAIL_TAG));
            }
            return user;
        } catch (JSONException e) {
            LOG.severe("Error parsing user JSON object");
            LOG.severe(e.toString());
            return new User();
        }
    }

    public static User fromJsonObject(JSONObject userObject) {
        User user = new User();
        try {
            if (userObject.has(ID_TAG)) {
                user.setId(userObject.getString(ID_TAG));
            }
            if (userObject.has(FIRST_NAME_TAG)) {
                user.setFirstName(userObject.getString(FIRST_NAME_TAG));
            }
            if (userObject.has(LAST_NAME_TAG)) {
                user.setLastName(userObject.getString(LAST_NAME_TAG));
            }
            if (userObject.has(EMAIL_TAG)) {
                user.setEmail(userObject.getString(EMAIL_TAG));
            }
            if (userObject.has(COMPANY_NAME_TAG)) {
                user.setCompanyName(userObject.getString(COMPANY_NAME_TAG));
            }
            if (userObject.has(PHOTO_URL_TAG)) {
                user.setPhotoUrl(userObject.getString(PHOTO_URL_TAG));
            }
            if (userObject.has(BCC_EMAIL_TAG)) {
                user.setBccEmail(userObject.getString(BCC_EMAIL_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing user JSON object");
            LOG.severe(e.toString());
        }
        return user;
    }

    public static List<User> fromJsonArray(JSONArray teamArray) {
        List<User> users = new ArrayList<>();
        for (int j = 0; j < teamArray.length(); j++) {
            JSONObject userObject;
            try {
                userObject = teamArray.getJSONObject(j);
                userObject = userObject.getJSONObject(USER_TAG);
                users.add(fromJsonObject(userObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing team (user) array");
                LOG.severe(e.toString());
            }
        }
        return users;
    }

    public static String toJsonObject(User user) {
        JSONObject userObject = new JSONObject();

        // TODO : create User object here!!
        addJsonStringValue(user.getId(), userObject, ID_TAG);
        addJsonStringValue(user.getAuthKey(), userObject, AUTH_KEY_TAG);
        addJsonStringValue(user.getAccountType(), userObject, ACCOUNT_TYPE_TAG);
        addJsonStringValue(user.getBccEmail(), userObject, BCC_EMAIL_TAG);
        addJsonStringValue(user.getCompanyName(), userObject, COMPANY_NAME_TAG);
        addJsonStringValue(user.getEmail(), userObject, EMAIL_TAG);
        addJsonStringValue(user.getFirstName(), userObject, FIRST_NAME_TAG);
        addJsonStringValue(user.getLastName(), userObject, LAST_NAME_TAG);
        addJsonStringValue(user.getPhotoUrl(), userObject, PHOTO_URL_TAG);

        // SALES ??

        // ACCOUNT

        // CALL RESULTS ??

        return userObject.toString();
    }

    public static User addCallResults(JSONObject dataObject, User user) {
        ArrayList<CallResult> callResults = new ArrayList<>();
        int index = 0;
        try {
            if (dataObject.has(CALL_RESULTS_TAG)) {
                JSONObject callResultsObject = dataObject.getJSONObject(CALL_RESULTS_TAG);
                Iterator<String> iterator = callResultsObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    try {
                        Object value = callResultsObject.get(key);
                        callResults.add(new CallResult()
                                        .setIntId(index)
                                        .setId(key)
                                        .setDisplay(value.toString())
                        );
                    } catch (JSONException e) {
                        LOG.severe("Failed to parse all values in call_results object");
                        LOG.severe(e.toString());
                    }
                    index++;
                }
            } else {
                callResults.add(new CallResult()
                                .setIntId(0)
                                .setId("interested")
                                .setDisplay("Interested")
                );
                callResults.add(new CallResult()
                                .setIntId(1)
                                .setId("not_interested")
                                .setDisplay("Not interested")
                );
                callResults.add(new CallResult()
                                .setIntId(2)
                                .setId("left_message")
                                .setDisplay("Left message")
                );
                callResults.add(new CallResult()
                                .setIntId(3)
                                .setId("no_answer")
                                .setDisplay("No answer")
                );
                callResults.add(new CallResult()
                                .setIntId(4)
                                .setId("other")
                                .setDisplay("Other")
                );
            }
        } catch (JSONException e) {
            LOG.severe("No call_results JSON object in response");
            LOG.severe(e.toString());
        }
        user.getAccount().setCallResults(callResults);
        return user;
    }
}
