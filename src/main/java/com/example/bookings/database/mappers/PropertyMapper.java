package com.example.bookings.database.mappers;

import com.example.bookings.database.models.User;
import com.example.bookings.domain.Property;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public Property toDomain(com.example.bookings.database.models.Property dbProperty) {
        return new Property(dbProperty.getId().toString(), dbProperty.getUser().getId().toString(),
                dbProperty.getName(), dbProperty.getLocation());
    }

    public com.example.bookings.database.models.Property toDb(CreatePropertyRequest request, User user) {
        return new com.example.bookings.database.models.Property()
                .setName(request.name())
                .setLocation(request.location())
                .setUser(user);
    }

    public com.example.bookings.database.models.Property toDb(UpdatePropertyRequest request,
                                                              com.example.bookings.database.models.Property dbProperty) {
        dbProperty.setName(request.name());
        dbProperty.setLocation(request.location());

        return dbProperty;
    }


}
