package com.kardoaward.kardo.participant.mapper;


import com.kardoaward.kardo.participant.dto.ParticipantDto;
import com.kardoaward.kardo.participant.model.Participant;

public class ParticipantMapper {

    public static ParticipantDto toParticipantDto(Participant participant) {
        return new ParticipantDto(
                participant.getId(),
                participant.getUser(),
                participant.getCompetition(),
                participant.getDirection(),
                participant.getUserStatus());
    }

    public static Participant toParticipant(ParticipantDto participantDto) {
        return new Participant(
                participantDto.getId(),
                participantDto.getUser(),
                participantDto.getCompetition(),
                participantDto.getDirection(),
                participantDto.getUserStatus()
        );
    }
}
