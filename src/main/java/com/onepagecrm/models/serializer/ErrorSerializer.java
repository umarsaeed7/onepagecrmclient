package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

public class ErrorSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ErrorSerializer.class.getName());

    public static OnePageException fromString(String responseBody) {
        OnePageException exception = new OnePageException();
        JSONObject responseObject;
        try {
            responseObject = new JSONObject(responseBody);
            int status = responseObject.getInt(STATUS_TAG);
            switch (status) {
                case 400:
                    exception = new BadRequestException();
                    break;
                case 401:
                    exception = new AuthenticationExpection();
                    break;
                case 403:
                    exception = new ForbiddenException();
                    break;
                case 404:
                    exception = new ResourceNotFoundException();
                    break;
                case 409:
                    exception = new PreconditionFailedException();
                    break;
                case 500:
                    exception = new ServerErrorException();
                    break;
            }
            exception
                    .setErrorName(responseObject.getString(ERROR_NAME_TAG))
                    .setStatus(responseObject.getInt(STATUS_TAG))
                    .setMessage(responseObject.getString(MESSAGE_TAG))
                    .setErrorMessage(responseObject.getString(ERROR_MESSAGE_TAG));

        } catch (JSONException e) {
            LOG.severe("Error parsing error tags from response body");
            LOG.severe(e.toString());
        }
        return exception;
    }
}
