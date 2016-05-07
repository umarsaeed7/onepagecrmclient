package com.onepagecrm.net.request;

import java.util.Map;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PostRequest extends SignedRequest {

    /**
     * Constructor which takes JSON string for request body.
     *
     * @param endpoint
     * @param query
     * @param jsonBody
     */
    public PostRequest(String endpoint, String query, String jsonBody) {
        this.requestBody = jsonBody;
        setType();
        setEndpointUrl(endpoint);
        if (query == null) {
            authenticate();
        } else {
            addQuery(query);
            authenticate();
        }
    }

    /**
     * Constructor which takes map of key-value pairs for request body.
     *
     * @param endpoint
     * @param query
     * @param paramBody
     */
    public PostRequest(String endpoint, String query, Map<String, String> paramBody) {
        this.params = paramBody;
        setType();
        setEndpointUrl(endpoint);
        if (query == null) {
            authenticate();
        } else {
            addQuery(query);
            authenticate();
        }
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }

    public PostRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        authData = new Authentication(Account.loggedInUser, Request.POST, endpointUrl, requestBody);
    }
}
