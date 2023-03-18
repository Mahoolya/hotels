package com.app.hotels.service.impl;

import com.app.hotels.domain.Booking;
import com.app.hotels.domain.exception.ResourceDoesNotExistException;
import com.app.hotels.repository.BookingRepository;
import com.app.hotels.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking confirm(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()) {
            throw new ResourceDoesNotExistException("Не существует бронирования с id " + id);
        }
        Booking bookingExisted = booking.get();
        bookingExisted.setConfirmed(true);
        return bookingRepository.save(bookingExisted);
    }

}
