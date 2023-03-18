package com.app.hotels.web.controller;

import com.app.hotels.domain.Booking;
import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.criteria.HotelCriteria;
import com.app.hotels.service.BookingService;
import com.app.hotels.service.HotelService;
import com.app.hotels.web.dto.HotelDto;
import com.app.hotels.web.mapper.HotelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final BookingService bookingService;

    @DeleteMapping("/hotels/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.delete(id);
    }

    @GetMapping("/hotels")
    public String getCreatePage(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "createHotel";
    }

    @GetMapping("/hotels")
    public String create(@Valid @ModelAttribute("hotel") HotelDto hotel, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("hotel");
            return "createHotel";
        }
        Hotel hotelMapped = hotelMapper.toEntity(hotel);
        hotelService.create(hotelMapped);
        return "redirect:/main";
    }

    @GetMapping("/hotels/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "updateHotel";
    }

    @PutMapping("/hotels")
    public String update(@Valid @ModelAttribute("hotel") HotelDto hotel, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("hotel");
            return "updateHotel";
        }
        Hotel hotelMapped = hotelMapper.toEntity(hotel);
        hotelService.update(hotelMapped);
        return "redirect:/main";
    }

    @GetMapping("/main/{currentPage}")
    public String getMainPage(Model model, @PathVariable int currentPage) {
        List<Hotel> hotels = hotelService.findAll(new HotelCriteria(), currentPage);
        model.addAttribute("hotels", hotels);
        return "main";
    }

    @GetMapping("/bookings")
    public String getBookingsPage(Model model) {
        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @PutMapping("/bookings/{id}")
    public String confirmBooking(Model model, @PathVariable Long id) {
        bookingService.confirm(id);
        return "redirect:/bookings";
    }

}
