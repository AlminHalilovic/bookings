package com.example.bookings.database;

import com.example.bookings.database.mappers.BookingMapper;
import com.example.bookings.database.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingsDatabaseService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
}
