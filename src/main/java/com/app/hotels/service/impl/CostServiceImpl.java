package com.app.hotels.service.impl;

import com.app.hotels.domain.Cost;
import com.app.hotels.repository.CostRepository;
import com.app.hotels.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {

    private final CostRepository costRepository;

    @Override
    public List<Cost> findAllByHotelId(Long id) {
        return costRepository.findAllByHotelId(id);
    }

    @Override
    public Cost create(Cost cost) {
        return costRepository.save(cost);
    }

}
