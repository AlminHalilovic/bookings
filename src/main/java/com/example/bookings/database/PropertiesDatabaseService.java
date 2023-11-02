package com.example.bookings.database;

import com.example.bookings.database.mappers.PropertyMapper;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.domain.Property;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import com.example.bookings.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookings.exceptions.EntityType.PROPERTY;
import static com.example.bookings.exceptions.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PropertiesDatabaseService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public List<Property> getProperties() {
        return propertyRepository.findAll().stream().map(propertyMapper::toDomain).toList();
    }

    public Property getProperty(String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            throw ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id);
        }

        com.example.bookings.database.model.Property property = propertyRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id)
        );

        return propertyMapper.toDomain(property);
    }

    public Property createProperty(CreatePropertyRequest createPropertyRequest) {
        com.example.bookings.database.model.Property property = propertyMapper.toDb(createPropertyRequest);
        property = propertyRepository.save(property);
        return propertyMapper.toDomain(property);
    }

    public Property updateProperty(String id, UpdatePropertyRequest updatePropertyRequest) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            throw ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id);
        }

        com.example.bookings.database.model.Property property = propertyRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id)
        );

        property = propertyMapper.toDb(updatePropertyRequest, property);
        propertyRepository.save(property);

        return propertyMapper.toDomain(property);

    }

}
