package com.example.bookings.integration;

import com.example.bookings.database.models.Property;
import com.example.bookings.database.models.Role;
import com.example.bookings.database.models.User;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.database.repositories.RoleRepository;
import com.example.bookings.database.repositories.UserRepository;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.response.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static com.example.bookings.domain.Role.ROLE_OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"data.seeding.enabled=false"})
@AutoConfigureMockMvc
public class PropertyControllerIntegrationTests {

    private final static String USER_EMAIL = "a@a.com";
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Role role = Role.builder()
                .name(ROLE_OWNER.name())
                .build();
        role = roleRepository.save(role);

        User user = User.builder()
                .email(USER_EMAIL)
                .firstName("Test")
                .lastName("Test")
                .password("Nimla123")
                .userRoles(Set.of(role))
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void cleanupProperties() {
        propertyRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @DisplayName("GET /api/v1/properties/{id}")
    @WithMockUser(value = USER_EMAIL)
    void testGetProperty() throws Exception {
        // GIVEN
        User user = userRepository.findByEmail(USER_EMAIL).get();
        Property property = Property.builder()
                .name("name")
                .location("location")
                .user(user)
                .build();

        property = propertyRepository.save(property);

        // WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/properties/{id}", property.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // THEN
        String responseString = mvcResult.getResponse().getContentAsString();
        Response<com.example.bookings.domain.Property> response =
                new ObjectMapper().readValue(responseString, new TypeReference<>() {
                });
        assertNotNull(response);
        assertNotNull(response.getPayload());
        assertEquals(response.getPayload().id(), property.getId().toString());
        assertEquals(response.getPayload().name(), property.getName());
        assertEquals(response.getPayload().location(), property.getLocation());
    }

    @Test
    @DisplayName("POST /api/v1/properties")
    @WithMockUser(username = USER_EMAIL, roles = {"OWNER"})
    void testCreateProperty() throws Exception {
        // GIVEN
        CreatePropertyRequest request = new CreatePropertyRequest("name", "location");
        String jsonString = new ObjectMapper().writeValueAsString(request);

        // WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isCreated())
                .andReturn();

        // THEN
        String responseString = mvcResult.getResponse().getContentAsString();
        Response<com.example.bookings.domain.Property> response =
                new ObjectMapper().readValue(responseString, new TypeReference<>() {
                });
        assertNotNull(response);
        assertNotNull(response.getPayload());
        assertNotNull(response.getPayload().id());
        assertEquals(response.getPayload().name(), request.name());
        assertEquals(response.getPayload().location(), request.location());
    }
}
