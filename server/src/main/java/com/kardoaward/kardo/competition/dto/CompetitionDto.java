package com.kardoaward.kardo.competition.dto;

import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.stage.model.StageOfCompetition;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDto {

    @NotBlank(message = "Название события не может быть пустым.")
    @Size(min = 3, max = 120)
    private String name;

    @Size(min = 20, max = 7000)
    @NotBlank(message = "Описание конкурса не может быть пустым.")
    private String description;

    private Set<Direction> direction;

    private StageOfCompetition stage;
}
