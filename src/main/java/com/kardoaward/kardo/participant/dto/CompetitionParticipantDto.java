package com.kardoaward.kardo.participant.dto;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionParticipantDto {

    private UserDto user;

    private CompetitionDto competition;

    private Set<Direction> direction;
}
