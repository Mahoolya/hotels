package com.app.hotels.service;

import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.criteria.HotelCriteria;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll(HotelCriteria hotelCriteria, int currentPage);

    Hotel findById(Long id);

    Hotel create(Hotel hotel);

    Hotel update(Hotel hotel);

    void delete(Long id);

    List<String> findAllCountries();

    List<String> findAllCities();

    List<Integer> findAllStars();

}
