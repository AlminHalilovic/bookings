package com.example.bookings.services;

import com.example.bookings.database.PropertiesDatabaseService;
import com.example.bookings.domain.Property;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertiesService {

    private final PropertiesDatabaseService propertiesDatabaseService;

    public List<Property> getProperties() {
        return propertiesDatabaseService.getProperties();
    }

    public Property getProperty(String id) {
        // todo do validations
        return propertiesDatabaseService.getProperty(id);
    }

    public Property createProperty(CreatePropertyRequest createPropertyRequest) {
        // todo do validations
        return propertiesDatabaseService.createProperty(createPropertyRequest);
    }

    public Property updateProperty(String id, UpdatePropertyRequest updatePropertyRequest) {
        // todo do validations
        return propertiesDatabaseService.updateProperty(id, updatePropertyRequest);
    }
}
