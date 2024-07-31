package com.kardoaward.kardo.competition.controller;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.competition.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/admin/competitions")
@Slf4j
@Validated
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionDto createCompetition (@RequestBody @Valid CompetitionDto competitionDto){
        log.info("Добавление нового конкурса {}", competitionDto.toString());
        return competitionService.createCompetition(competitionDto);
    }

    @DeleteMapping("/{competitionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompetition(@PathVariable @PositiveOrZero Long competitionId) {
        log.info("Удаление конкурса с id {}", competitionId);
        competitionService.deleteCompetition(competitionId);
    }

    @PatchMapping("/{competitionId}")
    @ResponseStatus(HttpStatus.OK)
    public CompetitionDto updateCompetition(@PathVariable @PositiveOrZero Long competitionId, @RequestBody @Valid CompetitionDto competitionDto){
        log.info("Изменение конкурса с id {}", competitionId);
        return competitionService.updateCompetition(competitionId, competitionDto);
    }
}
