package com.app.hotels.service;

import com.app.hotels.domain.Cost;

import java.util.List;

public interface CostService {

    List<Cost> findAllByHotelId(Long id);

    Cost create(Cost cost);

}
