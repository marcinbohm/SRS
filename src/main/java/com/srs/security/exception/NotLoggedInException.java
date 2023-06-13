package com.srs.security.exception;

public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException() {
        super("User is not logged in");
    }
}
