package com.example.bookings.domain.request;

import com.example.bookings.utils.validators.StartDateEndDateConstraint;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

@StartDateEndDateConstraint
public record UpdateBlockRequest(@FutureOrPresent LocalDate startDate,
                                 @FutureOrPresent LocalDate endDate) implements DateRangeRecord {
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }
}
