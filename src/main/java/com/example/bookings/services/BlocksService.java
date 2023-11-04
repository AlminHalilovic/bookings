package com.example.bookings.services;

import com.example.bookings.database.BlocksDatabaseService;
import com.example.bookings.database.BookingsDatabaseService;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBlockRequest;
import com.example.bookings.domain.request.UpdateBlockRequest;
import com.example.bookings.exceptions.ApplicationException;
import com.example.bookings.exceptions.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookings.exceptions.ExceptionType.NOT_AVAILABLE;

@Service
@RequiredArgsConstructor
public class BlocksService {

    private final BlocksDatabaseService blocksDatabaseService;
    private final BookingsDatabaseService bookingsDatabaseService;

    public Block getBlock(String id) {
        return blocksDatabaseService.getBlock(id);
    }

    public List<Block> getBlocksByPropertyId(String propertyId) {
        return blocksDatabaseService.getBlocksByPropertyId(propertyId);
    }

    public Block createBlock(CreateBlockRequest request) {
        List<Booking> existingBookings = bookingsDatabaseService.getBookingsBetweenDates(request.startDate(), request.endDate());
        if (!existingBookings.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BLOCK, NOT_AVAILABLE);
        }
        return blocksDatabaseService.createBlock(request);
    }

    public Block updateBlock(String id, UpdateBlockRequest request) {
        List<Booking> existingBookings = bookingsDatabaseService.getBookingsBetweenDates(request.startDate(), request.endDate());
        if (!existingBookings.isEmpty()) {
            throw ApplicationException.buildException(EntityType.BLOCK, NOT_AVAILABLE);
        }
        return blocksDatabaseService.updateBlock(id, request);
    }
}
