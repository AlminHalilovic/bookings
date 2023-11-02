package com.example.bookings.database.repositories;

import com.example.bookings.database.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends CrudRepository<Booking, Long> {
}
