package com.example.bookings.database;

import com.example.bookings.database.mappers.PropertyMapper;
import com.example.bookings.database.models.User;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.database.repositories.UserRepository;
import com.example.bookings.domain.Property;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import com.example.bookings.exceptions.ApplicationException;
import com.example.bookings.exceptions.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookings.exceptions.EntityType.PROPERTY;
import static com.example.bookings.exceptions.EntityType.USER;
import static com.example.bookings.exceptions.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PropertiesDatabaseService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final UserRepository userRepository;

    public List<Property> getPropertiesByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> ApplicationException.buildException(USER, ENTITY_NOT_FOUND, userEmail)
        );
        return propertyRepository.findByUserId(user.getId()).stream().map(propertyMapper::toDomain).toList();
    }

    public Property getProperty(String id) {
        long idLong = parseId(id, PROPERTY);
        com.example.bookings.database.models.Property property = propertyRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id)
        );

        return propertyMapper.toDomain(property);
    }

    public Property createProperty(CreatePropertyRequest createPropertyRequest, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> ApplicationException.buildException(USER, ENTITY_NOT_FOUND, userEmail)
        );

        com.example.bookings.database.models.Property property = propertyMapper.toDb(createPropertyRequest, user);
        property = propertyRepository.save(property);
        return propertyMapper.toDomain(property);
    }

    public Property updateProperty(String id, UpdatePropertyRequest updatePropertyRequest) {
        long idLong = parseId(id, PROPERTY);
        com.example.bookings.database.models.Property property = propertyRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, id)
        );

        property = propertyMapper.toDb(updatePropertyRequest, property);
        propertyRepository.save(property);

        return propertyMapper.toDomain(property);
    }

    public void deleteProperty(String id) {
        long idLong = parseId(id, PROPERTY);
        propertyRepository.deleteById(idLong);
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
