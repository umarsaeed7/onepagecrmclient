package com.onepagecrm.exceptions;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 02/12/2015.
 */
public class NoMorePagesException extends OnePageException {

    public NoMorePagesException(String message) {
        super(message);
    }

    public NoMorePagesException setErrorMessage(String errorMessage) {
        super.setErrorMessage(errorMessage);
        return this;
    }
}
