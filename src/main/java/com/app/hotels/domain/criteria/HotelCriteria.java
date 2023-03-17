package com.app.hotels.domain.criteria;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HotelCriteria {

    private List<String> cities;
    private List<String> countries;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private List<Integer> stars;

}
