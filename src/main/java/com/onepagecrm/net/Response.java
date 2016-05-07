package com.onepagecrm.net;

public class Response {

    private int responseCode;
    private String responseMessage;
    private String responseBody;

    public Response(int responseCode, String responseMessage, String responseBody) {
        this.setResponseCode(responseCode);
        this.setResponseMessage(responseMessage);
        this.setResponseBody(responseBody);
    }

    public Response() {

    }

    public String getResponseMessage() throws NullPointerException {
        return responseMessage;
    }

    public Response setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    public String getResponseBody() throws NullPointerException {
        return responseBody;
    }

    public Response setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public int getResponseCode() throws NullPointerException {
        return responseCode;
    }

    public Response setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    @Override
    public String toString() {
        return "Response [code=" + responseCode + ", message=" + responseMessage + ", body="
                + responseBody + "]";
    }

}