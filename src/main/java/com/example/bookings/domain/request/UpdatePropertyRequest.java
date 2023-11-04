package com.example.bookings.domain.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePropertyRequest(@NotEmpty String name, @NotEmpty String location) {
}
