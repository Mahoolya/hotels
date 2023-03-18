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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
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
    public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User userMapped = userMapper.toEntity(user);
        userService.create(userMapped);
        return "redirect:/login";
    }

    @GetMapping("/hotels/{currentPage}")
    public String getHotelsPage(Model model, @PathVariable int currentPage, @ModelAttribute("hotelCriteria") HotelCriteria hotelCriteria) {
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
        return "registration";
    }

    @GetMapping("/hotels/{id}")
    public String getHotel(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        List<Feedback> feedbacks = feedbackService.findAllByHotelId(id);
        model.addAttribute("feedbacks", feedbackMapper.toDto(feedbacks));
        return "hotel";
    }

    @GetMapping("/hotels/{id}/booking")
    public String getBookingPage(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        List<Cost> costs = costService.findAllByHotelId(id);
        model.addAttribute("costs", costMapper.toDto(costs));
        Booking booking = new Booking();
        model.addAttribute("booking", bookingMapper.toDto(booking));
        return "createBooking";
    }

    @PostMapping("/hotels/booking/{id}")
    public String createBooking(@Valid @ModelAttribute("booking") BookingDto booking, BindingResult bindingResult,
                                Model model, @PathVariable Long id, Principal principal) {
        if (bindingResult.hasErrors()){
            model.addAttribute("booking");
            return "createBooking";
        }
        Booking bookingMapped = bookingMapper.toEntity(booking);
        User user = userService.findByEmail(principal.getName());
        bookingMapped.setUser(user);
        bookingMapped.setCost(costService.findById(id));
        bookingService.create(bookingMapped);
        return "redirect:/hotels";
    }

    @GetMapping("/hotels/bookings/{id}")
    public String getMyBookingPage(Model model, @PathVariable("id") Long id){
        List<Booking> bookings = bookingService.findAllByUserId(id);
        model.addAttribute("bookings", bookingMapper.toDto(bookings));
        return "myBookings";
    }

    @DeleteMapping("/hotels/bookings/{id}")
    public String deleteBooking(Model model, @PathVariable("id") Long id){
        bookingService.delete(id);
        return "redirect:/myBookings";
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

    @GetMapping("/hotels/{id}/feedbacks")
    public String getFeedbackCreatePage(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotelMapper.toDto(hotel));
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedbackMapper.toDto(feedback));
        return "createFeedback";
    }

    @PostMapping("/hotels/{id}/feedbacks")
    public String createFeedback(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("feedback") FeedbackDto feedback,
                                 Principal principal){
        Hotel hotel = hotelService.findById(id);
        User user = userService.findByEmail(principal.getName());
        feedback.setUser(user);
        feedback.setHotel(hotel);
        feedbackService.create(feedbackMapper.toEntity(feedback));
        return "redirect:/hotels";
    }

}
