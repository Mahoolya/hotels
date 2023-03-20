package com.app.hotels.web.controller;

import com.app.hotels.domain.Booking;
import com.app.hotels.domain.Cost;
import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.criteria.HotelCriteria;
import com.app.hotels.service.BookingService;
import com.app.hotels.service.CostService;
import com.app.hotels.service.HotelService;
import com.app.hotels.web.dto.CostDto;
import com.app.hotels.web.dto.HotelDto;
import com.app.hotels.web.mapper.CostMapper;
import com.app.hotels.web.mapper.HotelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final BookingService bookingService;
    private final CostService costService;
    private final CostMapper costMapper;

    @DeleteMapping("/hotels/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.delete(id);
    }

    @GetMapping("/hotels")
    public String getCreatePage(Model model) {
        HotelDto hotel = new HotelDto();
        model.addAttribute("hotel", hotel);
        return "createHotel";
    }

    @PostMapping("/hotels")
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

    @GetMapping("/costs/{id}")
    public String getCostsPage(Model model, @PathVariable Long id) {
        List<Cost> costs = costService.findAllByHotelId(id);
        model.addAttribute("costs", costMapper.toDto(costs));
        return "costs";
    }

    @GetMapping("/costs")
    public String getCreateCostsPage(Model model) {
        CostDto cost = new CostDto();
        model.addAttribute("cost", cost);
        return "createCost";
    }

    @PostMapping("/costs")
    public String createCost(@Valid @ModelAttribute("cost") CostDto cost, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("cost");
            return "createCost";
        }
        Cost costMapped = costMapper.toEntity(cost);
        costService.create(costMapped);
        return "redirect:/main";
    }

    @GetMapping("/hotels/{id}")
    public String getUpdatePage(Model model, @PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
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

    @PostMapping("/bookings/manage/{id}")
    public String confirmBooking(Model model, @PathVariable Long id) {
        bookingService.confirm(id);
        return "redirect:/api/v1/admin/bookings";
    }

}
