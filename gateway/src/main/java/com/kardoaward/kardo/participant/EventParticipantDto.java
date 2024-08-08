package com.kardoaward.kardo.participant;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventParticipantDto {

    private UserDto user;

    private EventDto event;
}
