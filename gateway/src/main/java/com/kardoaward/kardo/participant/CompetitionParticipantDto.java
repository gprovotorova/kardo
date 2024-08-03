package com.kardoaward.kardo.participant;

import com.kardoaward.kardo.competition.CompetitionDto;
import com.kardoaward.kardo.direction.DirectionDto;
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

    private Set<DirectionDto> direction;

    private Integer points;
}
