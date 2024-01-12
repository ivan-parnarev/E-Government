package com.egovernment.main.domain.annotation.vote;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueVoteValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueVoteConstraint {
    String message() default "Duplicate vote for campaign and userPin";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
