package com.kardoaward.kardo.competition.mapper;


import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;


public class CompetitionMapper {

    public static CompetitionDto toParticipantDto(Competition competition) {
        return new CompetitionDto(
                competition.getId(),
                competition.getName(),
                competition.getDescription(),
                competition.getDirection(),
                competition.getStage()
        );
    }

    public static Competition toParticipant(CompetitionDto competitionDto) {
        return new Competition(
                competitionDto.getId(),
                competitionDto.getName(),
                competitionDto.getDescription(),
                competitionDto.getDirection(),
                competitionDto.getStage()
        );
    }
}
