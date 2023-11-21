package com.egovernment.egovbackend.domain.annotation.pin;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserPinConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserPin {
    String message() default "Invalid User PIN. It must contain only digits and be exactly 10 digits long.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}