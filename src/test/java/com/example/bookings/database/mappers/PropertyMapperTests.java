package com.example.bookings.database.mappers;

import com.example.bookings.database.models.Property;
import com.example.bookings.database.models.User;
import com.example.bookings.domain.request.CreatePropertyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyMapperTests {

    private PropertyMapper propertyMapper;

    @BeforeEach
    public void setUp() {
        propertyMapper = new PropertyMapper();
    }

    @Test
    public void toDomain_property_mapped_successfully() {
        // Given

        User dbUser = User.builder()
                .id(1L)
                .email("a@a.com")
                .password("pass")
                .firstName("First")
                .lastName("Last")
                .userRoles(null)
                .build();

        Property dbProperty = Property.builder()
                .id(1L)
                .name("name")
                .location("location")
                .user(dbUser)
                .build();

        // When
        com.example.bookings.domain.Property property = propertyMapper.toDomain(dbProperty);

        // Then
        assertNotNull(property);
        assertEquals(property.id(), dbProperty.getId().toString());
        assertEquals(property.name(), dbProperty.getName());
        assertEquals(property.location(), dbProperty.getLocation());
        assertEquals(property.ownerId(), dbUser.getId().toString());
    }

    @Test
    public void toDb_create_property_request_mapped_successfully() {
        // Given
        CreatePropertyRequest request = new CreatePropertyRequest("name", "location");

        User dbUser = User.builder()
                .id(1L)
                .email("a@a.com")
                .password("pass")
                .firstName("First")
                .lastName("Last")
                .userRoles(null)
                .build();

        // When
        Property dbProperty = propertyMapper.toDb(request, dbUser);

        // Then
        assertNotNull(dbProperty);
        assertEquals(dbProperty.getName(), request.name());
        assertEquals(dbProperty.getLocation(), request.location());
        assertEquals(dbProperty.getUser(), dbUser);
    }
}
