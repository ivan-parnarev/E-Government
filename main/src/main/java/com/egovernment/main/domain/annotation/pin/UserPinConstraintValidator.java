package com.egovernment.egovbackend.domain.annotation.pin;

import com.egovernment.egovbackend.validation.UserPinValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserPinConstraintValidator implements ConstraintValidator<ValidUserPin, String> {

    private final UserPinValidator userPinValidator = new UserPinValidator();

    @Override
    public void initialize(ValidUserPin constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userPin, ConstraintValidatorContext constraintValidatorContext) {
        return this.userPinValidator.isPinValid(userPin);
    }
}
