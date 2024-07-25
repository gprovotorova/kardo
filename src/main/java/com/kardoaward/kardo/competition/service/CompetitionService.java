package com.kardoaward.kardo.competition.service;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;

public interface CompetitionService {

    CompetitionDto createCompetition(CompetitionDto competitionDto);

    void deleteCompetition(Long competitionId);

    CompetitionDto updateCompetition(Long competitionId, CompetitionDto competitionDto);
}
