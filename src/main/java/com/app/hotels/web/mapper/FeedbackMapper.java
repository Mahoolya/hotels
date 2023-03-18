package com.app.hotels.web.mapper;

import com.app.hotels.domain.Feedback;
import com.app.hotels.web.dto.FeedbackDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDto toDto(Feedback feedback);

    Feedback toEntity(FeedbackDto feedbackDto);

    List<FeedbackDto> toDto(List<Feedback> feedbacks);

}
