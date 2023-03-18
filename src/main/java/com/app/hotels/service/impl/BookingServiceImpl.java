package com.app.hotels.service.impl;

import com.app.hotels.domain.Booking;
import com.app.hotels.repository.BookingRepository;
import com.app.hotels.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findAllByUserId(Long id) {
        return bookingRepository.findAllByUserId(id);
    }

}
