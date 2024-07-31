package com.kardoaward.kardo.participant.controller;

import com.kardoaward.kardo.participant.service.ParticipantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class ParticipantController {

    private ParticipantService participantService;

}
