package com.example.bookings.domain.request;

import jakarta.validation.constraints.NotEmpty;

public record CreatePropertyRequest(@NotEmpty String name, @NotEmpty String location) {
}
