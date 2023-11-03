package com.example.bookings.domain;

import java.util.List;

public record User(String id, String email, String password, List<Role> roles) {
}
