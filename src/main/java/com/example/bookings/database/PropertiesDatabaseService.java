package com.example.bookings.database;

import com.example.bookings.database.mappers.PropertyMapper;
import com.example.bookings.database.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertiesDatabaseService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
}
