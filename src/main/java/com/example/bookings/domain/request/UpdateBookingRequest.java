package com.example.bookings.domain.request;

import java.time.LocalDate;

public record UpdateBookingRequest(LocalDate startDate, LocalDate endDate) {
}
