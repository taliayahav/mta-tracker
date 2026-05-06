package com.talia.mtatracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String routeId;   // MTA's own ID, e.g. "A", "6", "L"

    @Column(nullable = false)
    private String longName;  // e.g. "8th Avenue Express"

    @Column(nullable = false)
    private String color;     // hex color, e.g. "0039A6"
}