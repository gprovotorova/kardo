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

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private UserType type;

    private String email;

    private String password;

    private LocalDate birthday;

    private CountryDto country;

    private RegionDto region;

    private String city;
}
