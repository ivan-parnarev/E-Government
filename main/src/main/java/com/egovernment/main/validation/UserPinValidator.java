package com.egovernment.main.validation;

public class UserPinValidator {

    public boolean isPinValid(String userPin) {
        if (userPin == null) {
            return false;
        }

        String regex = "^[0-9]{10}$";
        return userPin.matches(regex);
    }

}
