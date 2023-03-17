package com.app.hotels.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String email;

    private String name;

    private String surname;

    private LocalDate birthday;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks = new HashSet<>();

    public enum Role{

        ROLE_ADMIN,
        ROLE_USER;

    }

}

