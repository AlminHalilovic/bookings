package com.example.bookings;

import com.example.bookings.database.models.Role;
import com.example.bookings.database.models.User;
import com.example.bookings.database.repositories.RoleRepository;
import com.example.bookings.database.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.example.bookings.domain.Role.ROLE_OWNER;
import static com.example.bookings.domain.Role.ROLE_USER;

@SpringBootApplication
public class BookingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingsApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "data.seeding", name = "enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner seedDataOnStartup(RoleRepository roleRepository,
                                               UserRepository userRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            roleRepository.save(Role.builder().name(ROLE_USER.name()).build());
            Role ownerRole = roleRepository.save(Role.builder().name(ROLE_OWNER.name()).build());

            User user = User.builder()
                    .firstName("Owner")
                    .lastName("Sample")
                    .email("a@a.com")
                    .password(passwordEncoder.encode("owner"))
                    .userRoles(Set.of(ownerRole))
                    .build();
            userRepository.save(user);

        };
    }

}
