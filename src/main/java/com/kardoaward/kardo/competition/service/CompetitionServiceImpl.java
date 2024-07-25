package com.kardoaward.kardo.competition.service;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.competition.repository.CompetitionRepository;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    private ModelMapper mapper;

    @Override
    public CompetitionDto createCompetition(CompetitionDto competitionDto) {
        Competition competition = mapper.map(competitionDto,Competition.class);
        if(competitionRepository.existsByName(competition.getName())){
            throw new ObjectExistException("Конкурс с таким названием уже существует");
        }
        return mapper.map(competitionRepository.save(competition), CompetitionDto.class);
    }

    @Override
    public void deleteCompetition(Long competitionId) {
        if(competitionRepository.existsById(competitionId)){
            competitionRepository.deleteById(competitionId);
        } else {
            throw new ObjectNotFoundException("Конкурса с таким id не существует");
        }
    }

    @Override
    public CompetitionDto updateCompetition(Long competitionId, CompetitionDto competitionDto) {
        Competition competition = mapper.map(competitionDto,Competition.class);
        if(!competitionRepository.existsById(competitionId)) {
            throw new ObjectNotFoundException("Конкурса с таким id не существует");

        }
        return mapper.map(competitionRepository.save(competition), CompetitionDto.class);
    }
}
