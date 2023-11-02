package com.example.bookings.database.repositories;

import com.example.bookings.database.model.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
}
