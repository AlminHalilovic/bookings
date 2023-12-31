package com.example.bookings.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StartDateEndDateValidator.class)
@Documented
public @interface StartDateEndDateConstraint {
    String message() default "Invalid dates request.";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}