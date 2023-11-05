package com.example.bookings.controllers;

import com.example.bookings.domain.Booking;
import com.example.bookings.domain.request.CreateBookingRequest;
import com.example.bookings.domain.request.UpdateBookingRequest;
import com.example.bookings.domain.response.Response;
import com.example.bookings.services.BookingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingsController {
    private final BookingsService bookingsService;

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get Bookings by propertyId", tags = {"bookings"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Booking.class))
                    )})})
    public Response getBookingsByPropertyId(@PathVariable("propertyId") String propertyId, Principal principal) {
        return Response.ok()
                .setPayload(bookingsService.getBookingsByPropertyId(propertyId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Booking", tags = {"bookings"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Booking.class))})})
    public Response getBooking(
            @PathVariable("id") String id) {
        return Response
                .ok()
                .setPayload(bookingsService.getBooking(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Booking", tags = {"bookings"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response createBooking(@RequestBody @Valid CreateBookingRequest request, Principal principal) {
        return Response
                .created()
                .setPayload(bookingsService.createBooking(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Booking", tags = {"bookings"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response updateBooking(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdateBookingRequest request) {
        return Response
                .ok()
                .setPayload(bookingsService.updateBooking(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Booking", tags = {"bookings"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation")
    })
    public Response deleteBooking(
            @PathVariable("id") String id) {
        bookingsService.deleteBooking(id);
        return Response.noContent();
    }
}
