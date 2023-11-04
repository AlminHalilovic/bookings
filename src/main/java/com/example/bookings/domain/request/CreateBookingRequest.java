package com.example.bookings.domain.request;

import java.time.LocalDate;

public record CreateBookingRequest(String propertyId, LocalDate startDate, LocalDate endDate) {
    public CreateBookingRequest {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("startDate cannot be in the past");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("startDate cannot be in the past");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate cannot be after endDate");
        }
    }
}
