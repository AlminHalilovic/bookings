package com.example.bookings.database.repositories;

import com.example.bookings.database.models.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
    @Override
    List<Property> findAll();
    List<Property> findByUserId(long userId);

}
