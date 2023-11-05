package com.example.bookings.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Column(name = "startDate")
    LocalDate startDate;

    @Column(name = "endDate")
    LocalDate endDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyId", referencedColumnName = "id")
    private Property property;
}
