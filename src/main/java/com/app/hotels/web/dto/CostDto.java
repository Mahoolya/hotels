package com.app.hotels.web.dto;

import com.app.hotels.domain.Hotel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class CostDto {

    private Hotel hotel;

    @NotNull(message = "Тип комнаты обязательно")
    @Length(min = 3, max = 45, message = "Тип комнаты должен находиться в пределах от 3 до 45 символов")
    private String roomType;

    @DecimalMin(value = "100.0", message = "Цена должна быть более 100.00")
    @Digits(integer = 3, fraction = 2, message = "Пример - 100.00")
    @NotNull(message = "Цена обязательна")
    private BigDecimal price;

}
