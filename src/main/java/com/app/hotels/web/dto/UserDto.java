package com.app.hotels.web.dto;

import com.app.hotels.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class UserDto {

    @NotNull(message = "Id обязателен")
    private Long id;

    private User.Role role;

    @NotNull(message = "Пароль обязателен")
    @Length(min = 3, max = 45, message = "Пароль должна находиться в пределах от 5 до 40 символов")
    private String password;

    @NotNull(message = "Email обязательна")
    @Length(min = 3, max = 45, message = "Email должнен находиться в пределах от 3 до 45 символов")
    @Email(message = "Введите корректный email")
    private String email;

    @NotNull(message = "Имя обязательно")
    @Length(min = 3, max = 45, message = "Имя должно находиться в пределах от 3 до 45 символов")
    private String name;

    @NotNull(message = "Фамилия обязательна")
    @Length(min = 3, max = 45, message = "Фамилия должна находиться в пределах от 3 до 45 символов")
    private String surname;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Введите корректную дату")
    private LocalDate birthday;

}
