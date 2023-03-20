package com.app.hotels.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
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

    @Column(name = "min_price")
    private BigDecimal minPrice;

    @Column(name = "max_price")
    private BigDecimal maxPrice;

    @OneToMany(mappedBy = "hotel")
    private List<Cost> costs = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<Feedback> feedbacks = new ArrayList<>();

}
