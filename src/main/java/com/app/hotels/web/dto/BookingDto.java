package com.app.hotels.web.dto;

import com.app.hotels.domain.Cost;
import com.app.hotels.domain.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDto {

    private Long id;

    private User user;

    private Cost cost;

    private boolean isConfirmed;

    @NotNull(message = "Дата заезда обязательна")
    @Future(message = "Введите корректную дату")
    private LocalDate startDate;

    @NotNull(message = "Дата выезда обязательна")
    @Future(message = "Введите корректную дату")
    private LocalDate endDate;

    private BigDecimal price;

}
