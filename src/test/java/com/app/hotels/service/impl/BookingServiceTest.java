package com.app.hotels.service.impl;

import com.app.hotels.domain.Booking;
import com.app.hotels.domain.Cost;
import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.User;
import com.app.hotels.domain.exception.ResourceDoesNotExistException;
import com.app.hotels.repository.BookingRepository;
import com.app.hotels.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp(){
        bookingService = new BookingServiceImpl(bookingRepository);
    }

    @Test
    void verifyCreatePassedTest() {
        Booking booking = generateBooking();
        bookingService.create(booking);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void verifyUpdatePassedTest() {
        Booking booking = generateBooking();
        bookingService.update(booking);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void verifyDeletePassedTest() {
        Long bookingId = 1L;
        bookingService.delete(bookingId);
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }

    @Test
    void verifyFindAllByUserIdPassedTest() {
        Booking booking= generateBooking();
        List<Booking> bookings = List.of(booking);
        Long userId = 1L;
        when(bookingRepository.findAllByUserId(anyLong())).thenReturn(bookings);
        List<Booking> bookingsFound = bookingService.findAllByUserId(userId);
        assertEquals(bookings, bookingsFound, "Assert that booking and bookingFound are equals");
        verify(bookingRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void verifyFindAllPassedTest() {
        Booking booking= generateBooking();
        List<Booking> bookings = List.of(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);
        List<Booking> bookingsFound = bookingService.findAll();
        assertEquals(bookings, bookingsFound, "Assert that booking and bookingFound are equals");
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void verifyFindByIdPassedTest() {
        Booking booking= generateBooking();
        Long bookingId = 1l;
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        Booking bookingFound = bookingService.findById(bookingId);
        assertEquals(booking, bookingFound, "Assert that booking and bookingFound are equals");
        verify(bookingRepository, times(1)).findById(bookingId);
    }

    @Test
    void verifyFailedPassedTest() {
        Long bookingId = 1l;
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceDoesNotExistException.class, () -> bookingService.confirm(bookingId),
                "Assert ResourceDoesNotExistException");
        verify(bookingRepository, times(1)).findById(bookingId);
    }

    private User generateUser() {
        return new User(1L, User.Role.ROLE_USER, "katya123", "katya@mail.ru", "Katya", "Ivanova", LocalDate.now(),
                new ArrayList<>(), new ArrayList<>());
    }

    private Cost generateCost(){
        return new Cost(1L, generateHotel(), "Люкс", BigDecimal.valueOf(0), new ArrayList<>());
    }

    private Hotel generateHotel(){
        return new Hotel(1L, "Отель", "Минск", "Беларусь", "Все включено",
                5 , 750, "Отель", "hotel.jpg",
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), new ArrayList<>(), new ArrayList<>());
    }

    private Booking generateBooking() {
        return new Booking(1L, generateUser(), generateCost(), false, LocalDate.now().minusDays(4), LocalDate.now(),
                new BigDecimal(0));
    }

}
