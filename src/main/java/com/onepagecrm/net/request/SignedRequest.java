package com.onepagecrm.net.request;

import com.onepagecrm.net.Authentication;

public abstract class SignedRequest extends Request {

    protected Authentication authData;

    @Override
    public void setRequestHeaders() {
        super.setRequestHeaders();
        if (authData != null && authData.getUserId() != null) {
            connection.setRequestProperty(X_UID, authData.getUserId());
            LOG.info("X_UID=" + authData.getUserId());
            connection.setRequestProperty(X_TS, Integer.toString(authData.getUnixTime()));
            LOG.info("X_TS=" + Integer.toString(authData.getUnixTime()));
            connection.setRequestProperty(X_AUTH, authData.getSignature());
            LOG.info("X_AUTH=" + authData.getSignature());
            connection.setRequestProperty(X_SOURCE, SOURCE);
            LOG.info("X_SOURCE=" + SOURCE);
        }
    }

    public void setAuthData(Authentication authData) {
        this.authData = authData;
    }
}
