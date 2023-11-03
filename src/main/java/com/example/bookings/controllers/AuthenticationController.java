package com.example.bookings.controllers;

import com.example.bookings.domain.request.LoginRequest;
import com.example.bookings.domain.request.SignupRequest;
import com.example.bookings.domain.response.JwtAuthenticationResponse;
import com.example.bookings.domain.response.Response;
import com.example.bookings.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @Operation(summary = "Signup", tags = {"authentication"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtAuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response signup(@RequestBody @Valid SignupRequest request) {
        authenticationService.signup(request);
        return Response.ok();
    }

    @PostMapping("/login")
    @Operation(summary = "Login", tags = {"authentication"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtAuthenticationResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response login(@RequestBody @Valid LoginRequest request) {
        JwtAuthenticationResponse tokenResponse = authenticationService.login(request);
        return Response.ok().setPayload(tokenResponse);
    }
}
