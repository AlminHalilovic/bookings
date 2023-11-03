package com.example.bookings.services;

import com.example.bookings.database.repositories.UserDatabaseService;
import com.example.bookings.domain.Role;
import com.example.bookings.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDatabaseService userDatabaseService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDatabaseService.getUserByEmail(username);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.email())
                .password(user.password())
                .authorities(getAuthorities(user))
                .build();
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        List<Role> userRoles = user.roles();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userRoles.size());
        for (Role role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.name().toUpperCase(Locale.ROOT)));
        }

        return authorities;
    }


}
