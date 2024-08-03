package com.kardoaward.kardo.participant.dto;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.participant.model.CompetitionParticipant;
import com.kardoaward.kardo.stage.dto.StageDto;
import com.kardoaward.kardo.stage.model.StageOfCompetition;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
