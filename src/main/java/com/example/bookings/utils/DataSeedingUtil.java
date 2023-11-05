package com.example.bookings.utils;

import com.example.bookings.database.models.Property;
import com.example.bookings.database.models.Role;
import com.example.bookings.database.models.User;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.database.repositories.RoleRepository;
import com.example.bookings.database.repositories.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.bookings.domain.Role.ROLE_OWNER;
import static com.example.bookings.domain.Role.ROLE_USER;


@Component
@ConditionalOnProperty(prefix = "data.seeding", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class DataSeedingUtil {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDataConfig userDataConfig;

    @Bean
    public CommandLineRunner seedData() {
        return args -> {
            Role userRole = roleRepository.save(Role.builder().name(ROLE_USER.name()).build());
            Role ownerRole = roleRepository.save(Role.builder().name(ROLE_OWNER.name()).build());

            List<User> users = new ArrayList<>();

            // insert users
            for (UserData userData : userDataConfig.getUsers()) {
                Role role = ROLE_OWNER.name().equalsIgnoreCase(userData.getRole()) ? ownerRole : userRole;

                User user = User.builder()
                        .email(userData.getEmail())
                        .firstName(userData.getFirstName())
                        .lastName(userData.getLastName())
                        .password(passwordEncoder.encode(userData.getPassword()))
                        .userRoles(Collections.singleton(role))
                        .build();
                users.add(user);
            }

            userRepository.saveAll(users);

            // for each owner, insert a default property
            users.stream().filter(x -> ROLE_OWNER.name().equalsIgnoreCase(x.getUserRoles().stream().findFirst().get().getName()))
                    .forEach(x -> {
                        Property property = Property.builder()
                                .name("Property " + x.getId())
                                .location("Location " + x.getId())
                                .user(x)
                                .build();
                        propertyRepository.save(property);
                    });
        };
    }
}

@Configuration
@ConditionalOnProperty(prefix = "data.seeding", name = "enabled", havingValue = "true", matchIfMissing = true)
@ConfigurationProperties(prefix = "data.seeding")
@Getter
@Setter
class UserDataConfig {
    private List<UserData> users;
}

@Data
class UserData {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}