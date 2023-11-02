package com.example.bookings.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
