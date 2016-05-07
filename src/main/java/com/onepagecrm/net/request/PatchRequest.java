package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class PatchRequest extends SignedRequest {

    /**
     * Constructor which takes JSON string for request body.
     *
     * @param endpoint
     * @param query
     * @param jsonBody
     */
    public PatchRequest(String endpoint, String query, String jsonBody) {
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

    public PatchRequest(String endpoint) {
        setType();
        setEndpointUrl(endpoint);
        authData = new Authentication(Account.loggedInUser, Request.PATCH, endpointUrl, "");
    }

    @Override
    public void setType() {
        this.type = Type.PATCH;
    }

    public PatchRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        authData = new Authentication(Account.loggedInUser, Request.PATCH, endpointUrl, requestBody);
    }
}
