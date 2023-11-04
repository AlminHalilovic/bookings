package com.example.bookings.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
