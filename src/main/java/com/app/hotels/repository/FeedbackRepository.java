package com.app.hotels.repository;

import com.app.hotels.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findAllByHotelId(Long id);

}
