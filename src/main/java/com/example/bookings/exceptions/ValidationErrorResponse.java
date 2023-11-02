package com.example.bookings.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<ValidationViolation> violations = new ArrayList<>();

    public List<ValidationViolation> getViolations() {
        return violations;
    }

    public void setViolations(List<ValidationViolation> violations) {
        this.violations = violations;
    }
}