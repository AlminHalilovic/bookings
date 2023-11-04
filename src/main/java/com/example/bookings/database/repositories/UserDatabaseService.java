package com.example.bookings.database.repositories;

import com.example.bookings.database.mappers.UserMapper;
import com.example.bookings.domain.User;
import com.example.bookings.domain.request.SignupRequest;
import com.example.bookings.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.bookings.domain.Role.ROLE_USER;
import static com.example.bookings.exceptions.EntityType.USER;
import static com.example.bookings.exceptions.ExceptionType.DUPLICATE_ENTITY;
import static com.example.bookings.exceptions.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDatabaseService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public User getUserByEmail(String email) {
        com.example.bookings.database.models.User dbUser = userRepository.findByEmail(email)
                .orElseThrow(() -> ApplicationException.buildException(USER, ENTITY_NOT_FOUND, email));

        return userMapper.toDomain(dbUser);
    }

    public User registerUser(SignupRequest registerUserRequest) {
        var dbUser = userRepository.findByEmail(registerUserRequest.email());
        if (dbUser.isEmpty()) {
            var userRole = roleRepository.findByName(ROLE_USER.name());
            var newDbUser = userMapper.toDb(registerUserRequest, userRole);
            userRepository.save(newDbUser);
            return userMapper.toDomain(newDbUser);
        }
        throw ApplicationException.buildException(USER, DUPLICATE_ENTITY, registerUserRequest.email());
    }
}