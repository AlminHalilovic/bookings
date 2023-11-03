package com.example.bookings.database.mappers;

import com.example.bookings.domain.Role;
import com.example.bookings.domain.User;
import com.example.bookings.domain.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toDomain(com.example.bookings.database.models.User user) {
        List<Role> roles = user.getUserRoles().stream().map(x -> Role.valueOf(x.getName().toUpperCase())).toList();
        return new User(user.getId().toString(), user.getEmail(), user.getPassword(), roles);
    }

    public com.example.bookings.database.models.User toDb(SignupRequest registerUserRequest, com.example.bookings.database.models.Role role) {
        return com.example.bookings.database.models.User.builder()
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .userRoles(Set.of(role))
                .build();
    }
}
