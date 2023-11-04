package com.example.bookings.database;

import com.example.bookings.database.mappers.BookingMapper;
import com.example.bookings.database.models.Property;
import com.example.bookings.database.repositories.BookingRepository;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBookingRequest;
import com.example.bookings.domain.request.UpdateBookingRequest;
import com.example.bookings.exceptions.ApplicationException;
import com.example.bookings.exceptions.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.bookings.exceptions.EntityType.BOOKING;
import static com.example.bookings.exceptions.EntityType.PROPERTY;
import static com.example.bookings.exceptions.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookingsDatabaseService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final PropertyRepository propertyRepository;

    public Booking getBooking(String id) {
        long idLong = parseId(id, BOOKING);
        com.example.bookings.database.models.Booking Booking = bookingRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(BOOKING, ENTITY_NOT_FOUND, id)
        );

        return bookingMapper.toDomain(Booking);
    }

    public List<Booking> getBookingsByPropertyId(String propertyId) {
        long idLong = parseId(propertyId, PROPERTY);
        return bookingRepository.findByPropertyId(idLong).stream().map(bookingMapper::toDomain).toList();
    }

    public Booking createBooking(CreateBookingRequest request) {
        Property property = propertyRepository.findById(Long.parseLong(request.propertyId())).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, request.propertyId())
        );

        com.example.bookings.database.models.Booking booking = bookingMapper.toDb(request, property);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDomain(booking);
    }

    public Booking updateBooking(String id, UpdateBookingRequest request) {
        long idLong = parseId(id, BOOKING);
        com.example.bookings.database.models.Booking booking = bookingRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(BOOKING, ENTITY_NOT_FOUND, id)
        );

        booking = bookingMapper.toDb(request, booking);
        bookingRepository.save(booking);

        return bookingMapper.toDomain(booking);

    }

    public List<Booking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate)
                .stream().map(bookingMapper::toDomain).toList();
    }

    public void deleteBooking(String id) {
        long idLong = parseId(id, BOOKING);
        bookingRepository.deleteById(idLong);
    }

    public boolean bookingsExistBetweenDates(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.existsByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate);
    }


    private long parseId(String id, EntityType entityType) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            throw ApplicationException.buildException(entityType, ENTITY_NOT_FOUND, id);
        }
        return idLong;
    }
}
