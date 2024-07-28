package com.kardoaward.kardo.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.country.dto.CountryDto;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.region.dto.RegionDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    //TODO дописать

    @NotBlank(message = "Имя пользователя не может быть пустым.")
    @Size(min = 2, max = 250)
    private String name;

    @NotBlank(message = "Фамилия пользователя не может быть пустым.")
    @Size(min = 2, max = 250)
    private String surname;

    private String patronymic;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Email(message = "Неверный формат e-mail.")
    @NotEmpty(message = "Адрес электронной почты не может быть пустым.")
    @Size(min = 6, max = 254)
    private String email;

    @NotBlank(message = "Поле \"пароль\" не может быть пустым.")
    private String password;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate birthday;

    private CountryDto country;

    private RegionDto region;

    @NotBlank(message = "Поле \"город\" не может быть пустым.")
    private String city;
}
