package com.example.bookings.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
