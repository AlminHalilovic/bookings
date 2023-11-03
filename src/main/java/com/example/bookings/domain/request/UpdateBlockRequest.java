package com.example.bookings.domain.request;

import java.time.LocalDate;

public record UpdateBlockRequest(LocalDate startDate, LocalDate endDate) {
}
