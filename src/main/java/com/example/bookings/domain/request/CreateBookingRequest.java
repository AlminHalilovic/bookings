package com.example.bookings.domain.request;

import java.time.LocalDate;

public record CreateBookingRequest(String propertyId, LocalDate startDate, LocalDate endDate) {
}
