package com.egovernment.egovbackend.domain.annotation.census;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCensusValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCensusConstraint {
    String message() default "Duplicate vote for campaign and userPin";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
