package com.example.bookings.database.repositories;

import com.example.bookings.database.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

    @Override
    List<Role> findAll();
}
