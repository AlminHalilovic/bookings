package com.example.bookings.services;

import com.example.bookings.database.BookingsDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingsService {

    private final BookingsDatabaseService bookingsDatabaseService;
}
