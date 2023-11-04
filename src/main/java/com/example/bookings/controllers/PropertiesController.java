package com.example.bookings.controllers;

import com.example.bookings.domain.Property;
import com.example.bookings.domain.PropertyAvailability;
import com.example.bookings.domain.request.CreatePropertyRequest;
import com.example.bookings.domain.request.UpdatePropertyRequest;
import com.example.bookings.domain.response.Response;
import com.example.bookings.services.PropertiesService;
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
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final PropertiesService propertiesService;

    @GetMapping
    @Operation(summary = "Get All User Properties", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Property.class))
                    )})})
    public Response getAllUserProperties(Principal principal) {
        return Response
                .ok()
                .setPayload(propertiesService.getAllUserProperties(principal.getName()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Property", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Property.class))})})
    public Response getProperty(
            @PathVariable("id") String id) {
        return Response
                .ok()
                .setPayload(propertiesService.getProperty(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Property", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Property.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response createProperty(@RequestBody @Valid CreatePropertyRequest request, Principal principal) {
        return Response
                .created()
                .setPayload(propertiesService.createProperty(request, principal.getName()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Property", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Property.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response updateProperty(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdatePropertyRequest request) {
        return Response
                .ok()
                .setPayload(propertiesService.updateProperty(id, request));
    }

    @GetMapping("/{id}/availability")
    @Operation(summary = "Get Property Availability", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropertyAvailability.class))})})
    public Response getPropertyAvailability(
            @PathVariable("id") String id) {
        return Response
                .ok()
                .setPayload(propertiesService.getPropertyAvailability(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Property", tags = {"properties"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation")
    })
    public Response deleteProperty(
            @PathVariable("id") String id) {
        propertiesService.deleteProperty(id);
        return Response.noContent();
    }
}
