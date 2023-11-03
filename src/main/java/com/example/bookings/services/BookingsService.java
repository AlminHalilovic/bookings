package com.example.bookings.services;

import com.example.bookings.database.BookingsDatabaseService;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBookingRequest;
import com.example.bookings.domain.request.UpdateBookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingsService {
    private final BookingsDatabaseService bookingsDatabaseService;

    public Booking getBooking(String id) {
        return bookingsDatabaseService.getBooking(id);
    }

    List<Booking> getBookingsByPropertyId(String propertyId) {
        return bookingsDatabaseService.getBookingsByPropertyId(propertyId);
    }

    public Booking createBooking(CreateBookingRequest request) {
        // todo validations
        return bookingsDatabaseService.createBooking(request);
    }

    public Booking updateBooking(String id, UpdateBookingRequest request) {
        // todo validations
        return bookingsDatabaseService.updateBooking(id, request);
    }

}
