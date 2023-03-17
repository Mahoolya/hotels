package com.app.hotels.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String country;

    @Column(name = "nutrition_type")
    private String nutritionType;

    private int stars;

    private int rooms;

    private String description;

    private String image;

    @OneToMany(mappedBy = "hotel")
    private Set<Cost> costs = new HashSet<>();

    @OneToMany(mappedBy = "hotel")
    private Set<Feedback> feedbacks = new HashSet<>();

}
