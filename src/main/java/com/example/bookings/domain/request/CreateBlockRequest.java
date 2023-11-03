package com.example.bookings.domain.request;

import java.time.LocalDate;

public record CreateBlockRequest(String propertyId, LocalDate startDate, LocalDate endDate) {
}
