package com.kardoaward.kardo.participant.dto;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.enums.DirectionType;
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

    private Integer points;
}
