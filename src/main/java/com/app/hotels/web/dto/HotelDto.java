package com.app.hotels.web.dto;

import com.app.hotels.domain.Cost;
import com.app.hotels.domain.Feedback;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Data
public class HotelDto {

    private Long id;

    @NotNull(message = "Имя обязательно")
    @Length(min = 3, max = 45, message = "Имя должно находиться в пределах от 3 до 45 символов")
    private String name;

    @NotNull(message = "Город обязателен")
    @Length(min = 3, max = 45, message = "Город должен находиться в пределах от 3 до 45 символов")
    private String city;

    @NotNull(message = "Страна обязательна")
    @Length(min = 3, max = 45, message = "Страна должна находиться в пределах от 3 до 45 символов")
    private String country;

    @NotNull(message = "Тип питания обязателен")
    @Length(min = 3, max = 45, message = "Тип питания должен находиться в пределах от 3 до 45 символов")
    private String nutritionType;

    @NotNull(message = "Количество звезд обязательно")
    @Min(value = 1, message = "Минимальное значение 1")
    @Max(value = 5, message = "Максимальное значение 5")
    private int stars;

    @NotNull(message = "Количество комнат обязательно")
    @Min(value = 100, message = "Минимальное значение 100")
    @Max(value = 1000, message = "Максимальное значение 1000")
    private int rooms;

    @NotNull(message = "Описание обязательно")
    @Length(min = 3, max = 45, message = "Описание находиться в пределах от 20 до 1000 символов")
    private String description;

    @NotNull(message = "Название изображения обязательно")
    @Length(min = 3, max = 45, message = "Название изображения должно находиться в пределах от 3 до 45 символов")
    private String image;

    @DecimalMin(value = "100.0", message = "Цена должна быть более 100.00")
    @Digits(integer = 3, fraction = 2, message = "Пример - 100.00")
    @NotNull(message = "Минимальная цена обязательна")
    private BigDecimal minPrice;

    @DecimalMin(value = "100.0", message = "Цена должна быть более 100.00")
    @Digits(integer = 3, fraction = 2, message = "Пример - 100.00")
    @NotNull(message = "Максимальная цена обязательна")
    private BigDecimal maxPrice;

    private List<Feedback> feedbacks;

    private List<Cost> costs;

}
