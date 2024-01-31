package com.egovernment.egovbackend.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User Not Found");
    }
}
