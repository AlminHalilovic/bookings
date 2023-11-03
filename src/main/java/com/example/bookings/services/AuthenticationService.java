package com.example.bookings.services;

import com.example.bookings.database.repositories.UserDatabaseService;
import com.example.bookings.domain.request.LoginRequest;
import com.example.bookings.domain.request.SignupRequest;
import com.example.bookings.domain.response.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDatabaseService userDatabaseService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public void signup(SignupRequest request) {
        userDatabaseService.registerUser(request);
    }

    public JwtAuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        var userDetails = userDetailsService.loadUserByUsername(request.email());

        return new JwtAuthenticationResponse(jwtService.generateToken(userDetails));
    }

}
