package com.example.bookings.domain.request;

import java.time.LocalDate;

public record CreateBlockRequest(String propertyId,
                                 LocalDate startDate,
                                 LocalDate endDate) {
    public CreateBlockRequest {
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
