package com.kardoaward.kardo.competition.dto;

import com.kardoaward.kardo.Direction;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDto {

    private Long id;

    @NotBlank(message = "Название события не может быть пустым.")
    @Size(min = 3, max = 120)
    private String name;

    @Size(min = 20, max = 7000)
    @NotBlank(message = "Описание конкурса не может быть пустым.")
    private String description;

    private Direction direction;

    private Stage stage;
}
