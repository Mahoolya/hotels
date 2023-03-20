package com.app.hotels.web.controller;

import com.app.hotels.domain.*;
import com.app.hotels.domain.criteria.HotelCriteria;
import com.app.hotels.service.*;
import com.app.hotels.web.dto.BookingDto;
import com.app.hotels.web.dto.FeedbackDto;
import com.app.hotels.web.dto.UserDto;
import com.app.hotels.web.mapper.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final CostService costService;
    private final CostMapper costMapper;
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Неправильный пароль или email");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model)  {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new UserDto());
            return "registration";
        }
        User userMapped = userMapper.toEntity(user);
        userService.create(userMapped);
        return "redirect:/api/v1/users/login";
    }

    @GetMapping("/hotels/{currentPage}")
    public String getHotelsPage(Model model, @PathVariable(required = false) int currentPage, @ModelAttribute("hotelCriteria") HotelCriteria hotelCriteria, Principal principal) {
        List<Hotel> hotels = hotelService.findAll(hotelCriteria, currentPage);
        model.addAttribute("hotels", hotelMapper.toDto(hotels));
        List<String> cities = hotelService.findAllCities();
        model.addAttribute("cities", cities);
        List<String> countries = hotelService.findAllCountries();
        model.addAttribute("countries", countries);
        List<Integer> stars = hotelService.findAllStars();
        model.addAttribute("stars", stars);
        model.addAttribute("minCost", 0);
        model.addAttribute("maxCost", 1000);
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "hotels";
    }

    @GetMapping("/hotel/{id}")
    public String getHotel(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        List<Feedback> feedbacks = feedbackService.findAllByHotelId(id);
        model.addAttribute("feedbacks", feedbackMapper.toDto(feedbacks));
        return "hotel";
    }

    @GetMapping("/hotel/{id}/booking")
    public String getBookingPage(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        List<Cost> costs = costService.findAllByHotelId(id);
        model.addAttribute("costs", costs);
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        return "createBooking";
    }

    @PostMapping("/hotel/{hotel}/{hotelId}/booking")
    public String createBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingResult,
                                Model model, @PathVariable(name = "hotelId") Long hotelId, Principal principal) {
        if (bindingResult.hasErrors()){
            Hotel hotel = hotelService.findById(hotelId);
            model.addAttribute("hotel", hotelMapper.toDto(hotel));
            List<Cost> costs = hotel.getCosts();
            model.addAttribute("costs", costMapper.toDto(costs));
            model.addAttribute("booking");
            return "createBooking";
        }
        User user = userService.findByEmail(principal.getName());
        booking.setUser(user);
        bookingService.create(booking);
        return "redirect:/api/v1/users/hotels/0";
    }

    @GetMapping("/hotel/bookings/{userId}")
    public String getMyBookingPage(Model model, @PathVariable("userId") Long userId){
        List<Booking> bookings = bookingService.findAllByUserId(userId);
        model.addAttribute("bookings", bookings);
        return "myBookings";
    }

    @PostMapping("/hotel/bookings/{bookingId}")
    public String deleteBooking(Model model, @PathVariable("bookingId") Long bookingId){
        bookingService.delete(bookingId);
        return "hotels";
    }

    @GetMapping("/hotels/bookings/update/{id}")
    public String getUpdateBookingPage(Model model, @PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        model.addAttribute("booking", bookingMapper.toDto(booking));
        return "myBookings";
    }

    @PutMapping("/hotels/booking")
    public String updateBooking(@Valid @ModelAttribute("booking") BookingDto booking, BindingResult bindingResult,
                                Model model, @PathVariable Long id, Principal principal) {
        if (bindingResult.hasErrors()){
            model.addAttribute("booking");
            return "updateBooking";
        }
        Booking bookingMapped = bookingMapper.toEntity(booking);
        bookingService.update(bookingMapped);
        return "redirect:/myBookings";
    }

    @GetMapping("/hotel/{hotelId}/feedbacks")
    public String getFeedbackCreatePage(Model model, @PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelService.findById(hotelId);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        FeedbackDto feedback = new FeedbackDto();
        model.addAttribute("feedback", feedback);
        return "createFeedback";
    }

    @PostMapping("/hotel/{hotel}/{hotelId}/feedbacks")
    public String createFeedback(@Valid @ModelAttribute("feedback") FeedbackDto feedback, BindingResult bindingResult,
                                 @PathVariable("hotelId") Long hotelId, Principal principal, Model model){
        if (bindingResult.hasErrors()) {
            Hotel hotel = hotelService.findById(hotelId);
            User user = userService.findByEmail(principal.getName());
            feedback.setUser(user);
            feedback.setHotel(hotel);
            model.addAttribute("hotel", hotel);
            model.addAttribute("feedback", feedback);
            return "createFeedback";
        }
        Feedback feedbackMapped = feedbackMapper.toEntity(feedback);
        Hotel hotel = hotelService.findById(hotelId);
        User user = userService.findByEmail(principal.getName());
        feedbackMapped.setUser(user);
        feedbackMapped.setHotel(hotel);
        feedbackService.create(feedbackMapped);
        return "redirect:/api/v1/users/hotels/0";
    }

}
