package com.example.bookings.database.repositories;

import com.example.bookings.database.models.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {

    List<Block> findByPropertyId(Long propertyId);

    List<Block> findByStartDateBetweenOrEndDateBetween(LocalDate start, LocalDate end, LocalDate start2, LocalDate end2);

    boolean existsByStartDateBetweenOrEndDateBetween(LocalDate start, LocalDate end, LocalDate start2, LocalDate end2);

}
