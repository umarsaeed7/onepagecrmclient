package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Contact;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

public class ContactPhotoSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactPhotoSerializer.class.getName());

    public static Contact fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject parsedObject = new JSONObject(parsedResponse);
            return ContactSerializer.fromJsonObject(parsedObject);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Could not create JSON object from response");
            LOG.severe(e.toString());
        }
        return new Contact();
    }

    public static String toJsonObject(String base64EncodedImageString) {
        JSONObject imageObject = new JSONObject();
        addJsonStringValue(base64EncodedImageString, imageObject, IMAGE_TAG);
        return imageObject.toString();
    }
}
