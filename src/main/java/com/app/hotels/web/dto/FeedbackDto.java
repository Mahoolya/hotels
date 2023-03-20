package com.app.hotels.web.dto;

import com.app.hotels.domain.Hotel;
import com.app.hotels.domain.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class FeedbackDto {

    private Long id;

    private Hotel hotel;

    private User user;

    @NotNull(message = "Описание обязательно")
    @Length(min = 10, max = 1000, message = "Описание должно находиться в пределах от 10 до 1000 символов")
    private String text;

    @Min(value = 1, message = "Минимальное количество звезд 1")
    @Max(value = 5, message = "Максимальное количество звезд 5")
    @NotNull(message = "Количество звезд обязательно")
    private int stars;

}
