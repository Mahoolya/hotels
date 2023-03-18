package com.app.hotels.service.impl;

import com.app.hotels.domain.Feedback;
import com.app.hotels.repository.FeedbackRepository;
import com.app.hotels.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> findAllByHotelId(Long id) {
        return feedbackRepository.findAllByHotelId(id);
    }

}
