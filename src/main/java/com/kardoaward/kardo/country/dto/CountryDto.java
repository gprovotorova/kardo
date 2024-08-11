package com.kardoaward.kardo.country.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {

    private Long id;

    @NotBlank
    private String name;
}
