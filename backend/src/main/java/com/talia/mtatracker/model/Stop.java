package com.talia.mtatracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stops")
@Getter
@Setter
@NoArgsConstructor
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String stopId;     // MTA's ID, e.g. "127N"

    @Column(nullable = false)
    private String name;       // e.g. "Times Sq - 42nd St"

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}
