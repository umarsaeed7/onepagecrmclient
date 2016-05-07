package com.onepagecrm.exceptions;

/**
 * OnePageCRM HTTP response codes.
 * <p/>
 * 200 	OK - Operation successful.
 * <p/>
 * 400 	Bad Request. The request is not properly formatted, doesn’t properly specify response format or contains
 * improper parameters or data.
 * <p/>
 * 401 	Unauthorised. Authorization data is absent, invalid or expired. Generally this means that a login form should
 * be displayed to a user.
 * <p/>
 * 403 	Forbidden. Issued when user is logged in, but does not have permission for requested operation. This includes
 * cases when user has reached limits like allowed number of contacts.
 * <p/>
 * 404 	Resource Not Found. When an id was provided for a request but no resource exists for that object.
 * <p/>
 * 409 	Precondition Failed. Server state does not allow requested operation to be carried on. Issued in cases such as
 * when trying to add an assigned nextPage action to a contact which already has one assigned for a user.
 * <p/>
 * 500 	Internal Server Error.
 */

import java.util.List;

public class OnePageException extends Exception {

    private String errorName;
    private int status;
    private String message;
    private String errorMessage;
    private List<String> errors;

    public OnePageException() {
    }

    public OnePageException(String message) {
        this.setMessage(message);
    }

    public void throwOnePageException() throws OnePageException {
        throw this;
    }

    public String getErrorName() {
        return errorName;
    }

    public OnePageException setErrorName(String errorName) {
        this.errorName = errorName;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public OnePageException setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public OnePageException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OnePageException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public OnePageException setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }
}
