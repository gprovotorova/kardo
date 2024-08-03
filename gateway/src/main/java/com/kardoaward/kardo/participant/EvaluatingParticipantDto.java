package com.kardoaward.kardo.participant;

import com.kardoaward.kardo.competition.CompetitionDto;
import com.kardoaward.kardo.stage.dto.StageDto;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluatingParticipantDto {

    private Long id;

    private CompetitionDto competition;

    private StageDto stage;

    private CompetitionParticipantDto participant;

    private UserDto expert;

    private Double points;
}
