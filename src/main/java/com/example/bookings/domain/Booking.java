package com.example.bookings.domain;

import java.time.LocalDate;

public record Booking(String id, String userId, String propertyId, LocalDate startDate, LocalDate endDate) {
}
