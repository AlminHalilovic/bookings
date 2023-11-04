package com.example.bookings.database.repositories;

import com.example.bookings.database.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByPropertyId(Long propertyId);
    List<Booking> findByStartDateBetweenOrEndDateBetween(LocalDate start, LocalDate end, LocalDate start2, LocalDate end2);
}
