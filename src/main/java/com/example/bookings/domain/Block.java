package com.example.bookings.domain;

import java.time.LocalDate;

public record Block(String id, String ownerId, String propertyId, LocalDate startDate, LocalDate endDate) {
}
