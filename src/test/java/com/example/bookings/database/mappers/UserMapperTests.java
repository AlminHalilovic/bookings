package com.example.bookings.database.mappers;

import com.example.bookings.database.models.User;
import com.example.bookings.domain.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

import static com.example.bookings.domain.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTests {

    @Mock
    BCryptPasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userMapper = new UserMapper(passwordEncoder);
    }

    @Test
    public void toDomain_user_mapped_successfully() {
        // Given
        com.example.bookings.database.models.Role role = com.example.bookings.database.models.Role.builder()
                .name(ROLE_USER.name())
                .id(1L)
                .build();

        User dbUser = User.builder()
                .id(1L)
                .email("a@a.com")
                .password("pass")
                .firstName("First")
                .lastName("Last")
                .userRoles(Set.of(role))
                .build();

        // When
        com.example.bookings.domain.User user = userMapper.toDomain(dbUser);

        // Then
        assertNotNull(user);
        assertEquals(user.id(), dbUser.getId().toString());
        assertEquals(user.email(), dbUser.getEmail());
        assertEquals(user.password(), dbUser.getPassword());
    }

    @Test
    public void toDb_reqister_user_request_mapped_successfully() {
        // Given
        SignupRequest request = new SignupRequest("First", "Last", "a@a.com", "pass");
        String encodedPassword = "EncodedPass";

        com.example.bookings.database.models.Role role = com.example.bookings.database.models.Role.builder()
                .name(ROLE_USER.name())
                .id(1L)
                .build();

        Mockito.when(passwordEncoder.encode(request.password()))
                .thenReturn(encodedPassword);
        // When
        User user = userMapper.toDb(request, role);

        // Then
        assertNotNull(user);
        assertEquals(user.getEmail(), request.email());
        assertEquals(user.getFirstName(), request.firstName());
        assertEquals(user.getLastName(), request.lastName());
        assertEquals(user.getPassword(), encodedPassword);
    }
}