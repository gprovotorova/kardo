package com.kardoaward.kardo.admin.controller;

import com.kardoaward.kardo.admin.AdminClient;
import com.kardoaward.kardo.competition.CompetitionDto;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.streams.dto.StreamDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
@Validated
public class AdminController {

    private final AdminClient adminClient;

    @PostMapping("/events")
    public ResponseEntity createEvent (@RequestBody @Valid EventDto eventDto){
        log.info("Добавление нового события {}", eventDto.toString());
        return adminClient.createEvent(eventDto);
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity deleteEvent(@PathVariable @PositiveOrZero Long eventId) {
        log.info("Удаление события с id {}", eventId);
        return adminClient.deleteEvent(eventId);
    }

    @PatchMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateEvent(@PathVariable @PositiveOrZero Long eventId,
                                      @RequestBody @Valid EventDto eventDto){
        log.info("Изменение события с id {}", eventId);
        return adminClient.updateEvent(eventId, eventDto);
    }
    @PostMapping("/partners")
    public ResponseEntity createPartner(@RequestBody @Valid PartnerDto partnerDto) {
        log.info("Добавление нового партнера {}", partnerDto.toString());
        return adminClient.createPartner(partnerDto);
    }

    @DeleteMapping("/partners/{partnerId}")
    public ResponseEntity deletePartner(@PathVariable @PositiveOrZero Long partnerId) {
        log.info("Удаление партнера с id {}", partnerId);
        return adminClient.deletePartner(partnerId);
    }

    @PatchMapping("/partners/{partnerId}")
    public ResponseEntity updatePartner(@PathVariable @PositiveOrZero Long partnerId,
                                        @RequestBody @Valid PartnerDto partnerDto) {
        log.info("Изменение партнера с id {}", partnerId);
        return adminClient.updatePartner(partnerId, partnerDto);
    }

    @PostMapping("/streams")
    public ResponseEntity createStream(@RequestBody @Valid StreamDto streamDto) {
        log.info("Добавление нового стрима {} администратором",
                streamDto.toString());
        return adminClient.createStream(streamDto);
    }

    @PatchMapping("/streams/{streamId}")
    public ResponseEntity updateStream(@PathVariable @PositiveOrZero Long streamId,
                                  @RequestBody @Valid StreamDto streamDto) {
        log.info("Изменение данных стрима с id {} новыми данными {}",
                streamId, streamDto.toString());
        return adminClient.updateStream(streamId, streamDto);
    }

    @DeleteMapping("/streams/{streamId}")
    public ResponseEntity deleteStream(@PathVariable @PositiveOrZero Long streamId) {
        log.info("Удаление комментария с id {} ", streamId);
        return adminClient.deleteStream(streamId);
    }

    @PostMapping("/competitions")
    public ResponseEntity createCompetition (@RequestBody @Valid CompetitionDto competitionDto){
        log.info("Добавление нового конкурса {}", competitionDto.toString());
        return adminClient.createCompetition(competitionDto);
    }

    @DeleteMapping("/competitions/{competitionId}")
    public ResponseEntity deleteCompetition(@PathVariable @PositiveOrZero Long competitionId) {
        log.info("Удаление конкурса с id {}", competitionId);
        return adminClient.deleteCompetition(competitionId);
    }

    @PatchMapping("/competitions/{competitionId}")
    public ResponseEntity updateCompetition(@PathVariable @PositiveOrZero Long competitionId,
                                            @RequestBody @Valid CompetitionDto competitionDto){
        log.info("Изменение конкурса с id {}", competitionId);
        return adminClient.updateCompetition(competitionId, competitionDto);
    }
}
