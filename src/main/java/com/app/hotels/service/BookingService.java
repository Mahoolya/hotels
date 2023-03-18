package com.app.hotels.service;

import com.app.hotels.domain.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    Booking update(Booking booking);

    void delete(Long id);

    List<Booking> findAllByUserId(Long id);

    Booking confirm(Long id);

}
