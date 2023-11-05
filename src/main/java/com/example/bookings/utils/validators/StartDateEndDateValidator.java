package com.example.bookings.utils.validators;

import com.example.bookings.domain.request.DateRangeRecord;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class StartDateEndDateValidator implements ConstraintValidator<StartDateEndDateConstraint, Object> {

    @Override
    public void initialize(StartDateEndDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object request, ConstraintValidatorContext context) {
        if (request == null) {
            return true; // Let other constraints handle null values
        }

        boolean isValid = false;

        if (request instanceof DateRangeRecord dateRangeRecord) {
            isValid = isValidDateRange(dateRangeRecord.getStartDate(), dateRangeRecord.getEndDate());
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("startDate must be before endDate")
                    .addPropertyNode("startDate") // Field name
                    .addConstraintViolation();
        }

        return isValid;
    }

    private boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        return startDate.equals(endDate) || startDate.isBefore(endDate);
    }
}