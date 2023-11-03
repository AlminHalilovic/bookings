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
        return new com.example.bookings.database.models.Booking()
                .setStartDate(request.startDate())
                .setEndDate(request.endDate())
                .setUser(property.getUser())
                .setProperty(property);
    }

    public com.example.bookings.database.models.Booking toDb(UpdateBookingRequest request,
                                                             com.example.bookings.database.models.Booking dbBooking) {
        dbBooking.setStartDate(request.startDate());
        dbBooking.setEndDate(request.endDate());

        return dbBooking;
    }
}
