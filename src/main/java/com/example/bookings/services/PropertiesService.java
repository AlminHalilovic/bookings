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

    public List<Property> getProperties(String userEmail) {
        return propertiesDatabaseService.getPropertiesByUserEmail(userEmail);
    }

    public Property getProperty(String id) {
        Property property = propertiesDatabaseService.getProperty(id);
        return property;
    }

    public Property createProperty(CreatePropertyRequest createPropertyRequest, String userEmail) {
        return propertiesDatabaseService.createProperty(createPropertyRequest, userEmail);
    }

    public Property updateProperty(String id, UpdatePropertyRequest updatePropertyRequest) {
        return propertiesDatabaseService.updateProperty(id, updatePropertyRequest);
    }
}
