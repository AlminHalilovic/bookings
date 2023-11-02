package com.example.bookings.database.mappers;

import com.example.bookings.domain.Property;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public Property toDomain(com.example.bookings.database.model.Property dbProperty) {
        return new Property(dbProperty.getId().toString(), null, dbProperty.getName(), dbProperty.getLocation());
    }

    public com.example.bookings.database.model.Property toDb(CreatePropertyRequest request) {
        return new com.example.bookings.database.model.Property()
                .setName(request.name())
                .setLocation(request.location());
    }

    public com.example.bookings.database.model.Property toDb(UpdatePropertyRequest request,
                                                             com.example.bookings.database.model.Property dbProperty) {
        dbProperty.setName(request.name());
        dbProperty.setLocation(request.location());

        return dbProperty;
    }


}
