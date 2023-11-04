package com.example.bookings.domain.request;

import java.time.LocalDate;

public interface DateRangeRecord {
    LocalDate getStartDate();

    LocalDate getEndDate();
}