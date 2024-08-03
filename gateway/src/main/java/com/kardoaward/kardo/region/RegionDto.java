package com.kardoaward.kardo.region;

import com.kardoaward.kardo.country.CountryDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegionDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private CountryDto country;
}
