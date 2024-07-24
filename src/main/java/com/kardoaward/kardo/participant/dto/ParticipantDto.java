package com.kardoaward.kardo.participant.dto;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDto {

    private Long id;

    private User user;

    private Competition competition;

    private Direction direction;

    private String userStatus;
}
