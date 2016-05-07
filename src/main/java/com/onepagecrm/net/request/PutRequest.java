package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PutRequest extends SignedRequest {

    /**
     * Constructor which takes JSON string for request body.
     *
     * @param endpoint
     * @param query
     * @param jsonBody
     */
    public PutRequest(String endpoint, String query, String jsonBody) {
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

    public PutRequest(String endpoint) {
        setType();
        setEndpointUrl(endpoint);
        authData = new Authentication(Account.loggedInUser, Request.PUT, endpointUrl, "");
    }

    @Override
    public void setType() {
        this.type = Type.PUT;
    }

    public PutRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        authData = new Authentication(Account.loggedInUser, Request.PUT, endpointUrl, requestBody);
    }
}
