package com.onepagecrm.models.serializer;

import com.onepagecrm.models.CustomField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomFieldSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CustomFieldSerializer.class.getName());

    public static List<CustomField> fromString(String responseBody) {
        List<CustomField> customFields = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            String message = responseObject.getString(MESSAGE_TAG);

            if (status == 0 && message.equalsIgnoreCase(OK_TAG)) {
                JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);
                JSONArray customFieldsArray = dataObject.getJSONArray(CUSTOM_FIELDS_TAG);
                customFields = fromJsonArray(customFieldsArray);
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing CustomField's from response body");
            LOG.severe(e.toString());
        }
        return customFields;
    }

    public static List<CustomField> fromJsonArray(JSONArray customFieldArray) {
        List<CustomField> customFields = new ArrayList<>();
        for (int i = 0; i < customFieldArray.length(); i++) {
            try {
                JSONObject outerObject = customFieldArray.getJSONObject(i);
                CustomField customField = fromJsonObject(outerObject);
                customFields.add(customField);
            } catch (JSONException e) {
                LOG.severe("Error parsing CustomField's from JsonArray");
                LOG.severe(e.toString());
            }
        }
        return customFields;
//        // Re-sort the custom fields by their "position" field.
//        List<CustomField> orderedCustomFields = new ArrayList<>();
//        for (int i = 0; i < customFields.size(); i++) {
//            for (int j = 0; j < customFields.size(); j++) {
//                if (customFields.get(j).getPosition() == i) {
//                    orderedCustomFields.add(i, customFields.get(j));
//                }
//            }
//        }
//        return orderedCustomFields;
    }

    public static CustomField fromJsonObject(JSONObject outerObject) {
        CustomField customField = new CustomField();
        try {
            if (outerObject.has(VALUE_TAG)) {
                customField.setValue(CustomFieldValueSerializer.fromJsonObject(outerObject));
            }
            JSONObject customFieldObject = outerObject.getJSONObject(CUSTOM_FIELD_TAG);
            String id = customFieldObject.getString(ID_TAG);

            JSONArray choicesArray = customFieldObject.getJSONArray(CHOICES_TAG);
            List<String> choices = toListOfStrings(choicesArray);

            if (!choices.isEmpty()) {
                customField.setChoices(choices);
            }

            String name = customFieldObject.getString(NAME_TAG);
            int position = customFieldObject.getInt(POSITION_TAG);
            String type = customFieldObject.getString(TYPE_TAG);

            if (customFieldObject.has(REMINDER_DAYS_TAG)) {
                if (!customFieldObject.isNull(REMINDER_DAYS_TAG)) {
                    int reminderDays = customFieldObject.getInt(REMINDER_DAYS_TAG);
                    customField.setReminderDays(reminderDays);
                } else {
                    // TODO : review this.
                    // Does it make sense to set this to -1 when null returned??
                    customField.setReminderDays(-1);
                }
            }

            customField.setId(id)
                    .setName(name)
                    .setPosition(position)
                    .setType(type);

        } catch (JSONException e) {
            LOG.severe("Error parsing CustomField from JsonObject");
            LOG.severe(e.toString());
        }
        return customField;
    }

    public static String toJsonObject(CustomField customField) {
        JSONObject object = new JSONObject();
        JSONObject customFieldObject = new JSONObject();
        addJsonStringValue(customField.getId(), customFieldObject, ID_TAG);

        try {
            JSONArray choicesArray = new JSONArray(getChoicesJsonArray(customField));
            // Adds empty array with key.
            customFieldObject.put(CHOICES_TAG, choicesArray);
            // Adds no array when it's empty.
//            addJsonArray(choicesArray, customFieldObject, CHOICES_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONArray out of CustomField choices");
            LOG.severe(e.toString());
        }
        addJsonStringValue(customField.getName(), customFieldObject, NAME_TAG);
        addJsonIntegerValue(customField.getPosition(), customFieldObject, POSITION_TAG);
        addJsonStringValue(customField.getType(), customFieldObject, TYPE_TAG);
        addJsonIntegerValue(customField.getReminderDays(), customFieldObject, REMINDER_DAYS_TAG);
        addJsonObject(customFieldObject, object, CUSTOM_FIELD_TAG);
        CustomFieldValueSerializer.toJsonObject(customField.getValue(), object);
        return object.toString();
    }

    public static String toJsonObjectNew(CustomField customField) {
        JSONObject customFieldObject = new JSONObject();
        addJsonStringValue(customField.getId(), customFieldObject, ID_TAG);

        try {
            JSONArray choicesArray = new JSONArray(getChoicesJsonArray(customField));
            // Adds empty array with key.
            customFieldObject.put(CHOICES_TAG, choicesArray);
        } catch (JSONException e) {
            LOG.severe("Error creating JSONArray out of CustomField choices");
            LOG.severe(e.toString());
        }
        addJsonStringValue(customField.getName(), customFieldObject, NAME_TAG);
        addJsonIntegerValue(customField.getPosition(), customFieldObject, POSITION_TAG);
        addJsonStringValue(customField.getType(), customFieldObject, TYPE_TAG);
        addJsonIntegerValue(customField.getReminderDays(), customFieldObject, REMINDER_DAYS_TAG);
        CustomFieldValueSerializer.toJsonObject(customField.getValue(), customFieldObject);
        return customFieldObject.toString();
    }

    public static String toJsonArray(List<CustomField> customFields) {
        JSONArray customFieldsArray = new JSONArray();
        if (customFields != null && !customFields.isEmpty()) {
            for (int i = 0; i < customFields.size(); i++) {
                try {
                    customFieldsArray.put(new JSONObject(toJsonObject(customFields.get(i))));
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of CustomField");
                    LOG.severe(e.toString());
                }
            }
        }
        return customFieldsArray.toString();
    }

    private static String getChoicesJsonArray(CustomField customField) {
        return toJsonStringArray(customField.getChoices()).toString();
    }
}
