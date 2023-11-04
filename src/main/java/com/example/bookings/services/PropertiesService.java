package com.example.bookings.services;

import com.example.bookings.database.BlocksDatabaseService;
import com.example.bookings.database.BookingsDatabaseService;
import com.example.bookings.database.PropertiesDatabaseService;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.Property;
import com.example.bookings.domain.PropertyAvailability;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertiesService {

    private final PropertiesDatabaseService propertiesDatabaseService;
    private final BlocksDatabaseService blocksDatabaseService;
    private final BookingsDatabaseService bookingsDatabaseService;

    public List<Property> getProperties(String userEmail) {
        return propertiesDatabaseService.getPropertiesByUserEmail(userEmail);
    }

    public Property getProperty(String id) {
        return propertiesDatabaseService.getProperty(id);
    }

    public Property createProperty(CreatePropertyRequest createPropertyRequest, String userEmail) {
        return propertiesDatabaseService.createProperty(createPropertyRequest, userEmail);
    }

    public Property updateProperty(String id, UpdatePropertyRequest updatePropertyRequest) {
        return propertiesDatabaseService.updateProperty(id, updatePropertyRequest);
    }

    public PropertyAvailability getPropertyAvailability(String id) {
        List<Block> blocks = blocksDatabaseService.getBlocksByPropertyId(id);
        List<Booking> bookings = bookingsDatabaseService.getBookingsByPropertyId(id);
        return new PropertyAvailability(bookings, blocks);
    }
}
