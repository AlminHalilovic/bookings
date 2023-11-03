package com.example.bookings.database.repositories;

import com.example.bookings.database.models.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository  extends CrudRepository<Block, Long> {
}
