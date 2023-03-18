package com.app.hotels.service;

import com.app.hotels.domain.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback create(Feedback feedback);

    List<Feedback> findAllByHotelId(Long id);

}
