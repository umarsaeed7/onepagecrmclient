package com.onepagecrm.net.request;

import com.onepagecrm.models.Account;
import com.onepagecrm.net.Authentication;

public class DeleteRequest extends SignedRequest {

    public DeleteRequest(String endpoint) {
        setType();
        setEndpointUrl(endpoint);
        authData = new Authentication(Account.loggedInUser, Request.DELETE, endpointUrl, requestBody);
    }

    public DeleteRequest(String endpoint, String query) {
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
        this.type = Type.DELETE;
    }

    public DeleteRequest addQuery(String query) {
        this.endpointUrl += query;
        return this;
    }

    public void authenticate() {
        setRequestBody();
        authData = new Authentication(Account.loggedInUser, Request.GET, endpointUrl, requestBody);
    }
}
