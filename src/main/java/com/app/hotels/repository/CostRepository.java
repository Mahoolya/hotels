package com.app.hotels.repository;

import com.app.hotels.domain.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost, Long> {

    List<Cost> findAllByHotelId(Long id);

}
