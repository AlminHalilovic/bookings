package com.example.bookings.controllers;

import com.example.bookings.domain.Property;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final PropertiesService propertiesService;

    @GetMapping
    @Operation(summary = "Get Properties", tags = {"properties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Property.class))
                    )})})
    public Response getProperties() {
        return Response.ok()
                .setPayload(propertiesService.getProperties());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Property", tags = {"properties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Property.class))})})
    public Response getProperty(@PathVariable("id") String id) {
        return Response
                .ok()
                .setPayload(propertiesService.getProperty(id));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Property", tags = {"properties"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Property.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response createProperty(@RequestBody @Valid CreatePropertyRequest request) {
        return Response
                .created()
                .setPayload(propertiesService.createProperty(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Property", tags = {"properties"})
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
}
