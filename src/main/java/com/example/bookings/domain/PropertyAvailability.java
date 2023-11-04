package com.example.bookings.domain;

import java.util.List;

public record PropertyAvailability(List<Booking> bookings, List<Block> blocks) {
}
