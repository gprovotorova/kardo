package com.kardoaward.kardo.competition.controller;

import com.kardoaward.kardo.competition.service.CompetitionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class CompetitionController {

    private final CompetitionService competitionService;

}
