package com.example.bookings.controllers;

import com.example.bookings.domain.Block;
import com.example.bookings.domain.request.CreateBlockRequest;
import com.example.bookings.domain.request.UpdateBlockRequest;
import com.example.bookings.domain.response.Response;
import com.example.bookings.services.BlocksService;
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
@RequestMapping("/api/v1/blocks")
@RequiredArgsConstructor
public class BlocksController {
    private final BlocksService blocksService;

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get Blocks by propertyId", tags = {"blocks"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Block.class))
                    )})})
    public Response getBlocksByPropertyId(@PathVariable("propertyId") String propertyId) {
        return Response
                .ok()
                .setPayload(blocksService.getBlocksByPropertyId(propertyId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Block", tags = {"blocks"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Block.class))})})
    public Response getBlock(
            @PathVariable("id") String id) {
        return Response
                .ok()
                .setPayload(blocksService.getBlock(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Block", tags = {"blocks"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Block.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response createBlock(@RequestBody @Valid CreateBlockRequest request, Principal principal) {
        return Response
                .created()
                .setPayload(blocksService.createBlock(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Block", tags = {"blocks"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Block.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public Response updateBlock(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdateBlockRequest request) {
        return Response
                .ok()
                .setPayload(blocksService.updateBlock(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Block", tags = {"blocks"}, security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation")
    })
    public Response deleteBlock(
            @PathVariable("id") String id) {
        blocksService.deleteBlock(id);
        return Response.noContent();
    }
}
