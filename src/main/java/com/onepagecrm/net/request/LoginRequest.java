package com.onepagecrm.net.request;

import com.onepagecrm.models.serializer.BaseSerializer;
import com.onepagecrm.models.serializer.LoginSerializer;

public class LoginRequest extends Request {

    public LoginRequest(String username, String password) {
        setType();
        setEndpointUrl(BaseSerializer.LOGIN_TAG);
        this.requestBody = LoginSerializer.toJsonObject(username, password);
    }

    @Override
    public void setType() {
        this.type = Type.POST;
    }
}
