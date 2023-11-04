package com.example.bookings.services;

import com.example.bookings.database.BlocksDatabaseService;
import com.example.bookings.database.BookingsDatabaseService;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBookingRequest;
import com.example.bookings.domain.request.UpdateBookingRequest;
import com.example.bookings.exceptions.ApplicationException;
import com.example.bookings.exceptions.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookings.exceptions.ExceptionType.NOT_AVAILABLE;

@Service
@RequiredArgsConstructor
public class BookingsService {
    private final BookingsDatabaseService bookingsDatabaseService;
    private final BlocksDatabaseService blocksDatabaseService;

    public Booking getBooking(String id) {
        return bookingsDatabaseService.getBooking(id);
    }

    public List<Booking> getBookingsByPropertyId(String propertyId) {
        return bookingsDatabaseService.getBookingsByPropertyId(propertyId);
    }

    public Booking createBooking(CreateBookingRequest request) {
        List<Block> existingBlocks = blocksDatabaseService.getBlocksBetweenDates(request.startDate(), request.endDate());
        if (!existingBlocks.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BOOKING, NOT_AVAILABLE);
        }

        List<Booking> existingBookings = bookingsDatabaseService.getBookingsBetweenDates(request.startDate(), request.endDate());
        if (!existingBookings.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BOOKING, NOT_AVAILABLE);
        }

        return bookingsDatabaseService.createBooking(request);
    }

    public Booking updateBooking(String id, UpdateBookingRequest request) {
        List<Block> existingBlocks = blocksDatabaseService.getBlocksBetweenDates(request.startDate(), request.endDate());
        if (!existingBlocks.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BOOKING, NOT_AVAILABLE);
        }

        List<Booking> existingBookings = bookingsDatabaseService.getBookingsBetweenDates(request.startDate(), request.endDate());
        if (!existingBookings.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BOOKING, NOT_AVAILABLE);
        }
        return bookingsDatabaseService.updateBooking(id, request);
    }

}
