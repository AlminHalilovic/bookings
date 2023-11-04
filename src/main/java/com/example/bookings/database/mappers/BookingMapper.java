package com.example.bookings.database.mappers;

import com.example.bookings.database.models.Property;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBookingRequest;
import com.example.bookings.domain.request.UpdateBookingRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public Booking toDomain(com.example.bookings.database.models.Booking dbBooking) {
        return new Booking(dbBooking.getId().toString(), dbBooking.getUser().getId().toString(),
                dbBooking.getProperty().getId().toString(), dbBooking.getStartDate(), dbBooking.getEndDate());
    }

    public com.example.bookings.database.models.Booking toDb(CreateBookingRequest request, Property property) {

        return com.example.bookings.database.models.Booking.builder()
                .startDate(request.startDate())
                .endDate(request.endDate())
                .property(property)
                .user(property.getUser())
                .build();
    }

    public com.example.bookings.database.models.Booking toDb(UpdateBookingRequest request,
                                                             com.example.bookings.database.models.Booking dbBooking) {
        return com.example.bookings.database.models.Booking.builder()
                .id(dbBooking.getId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .property(dbBooking.getProperty())
                .user(dbBooking.getUser())
                .build();
    }
}
