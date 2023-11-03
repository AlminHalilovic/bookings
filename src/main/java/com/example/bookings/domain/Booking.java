package com.example.bookings.domain;

import java.time.LocalDate;

public record Booking(String id, String ownerId, String propertyId, LocalDate startDate, LocalDate endDate) {
}
