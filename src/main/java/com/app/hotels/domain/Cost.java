package com.app.hotels.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "costs")
@Data
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "room_type")
    private String roomType;

    private BigDecimal price;

    @OneToMany(mappedBy = "cost")
    private Set<Booking> bookings = new HashSet<>();

}
