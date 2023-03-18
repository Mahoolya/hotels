package com.app.hotels.repository;

import com.app.hotels.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "select distinct country from hotels", nativeQuery = true)
    List<String> findAllCountries();

    @Query(value = "select distinct city from hotels", nativeQuery = true)
    List<String> findAllCities();

    @Query(value = "select distinct stars from hotels", nativeQuery = true)
    List<Integer> findAllStars();

}
