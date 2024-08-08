package com.kardoaward.kardo.admin.controller;

import com.kardoaward.kardo.admin.service.AdminService;
import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController("AdminClassController")
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private static final String USER = "Authorization";

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@RequestHeader(USER) long userId, @RequestBody @Valid EventDto eventDto) {
        log.info("Добавление нового события {}", eventDto.toString());
        return adminService.createEvent(userId, eventDto);
    }

    @DeleteMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@RequestHeader(USER) long userId, @PathVariable @PositiveOrZero Long eventId) {
        log.info("Удаление события с id {}", eventId);
        adminService.deleteEvent(userId, eventId);
    }

    @PatchMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEvent(@RequestHeader(USER) long userId, @PathVariable @PositiveOrZero Long eventId, @RequestBody @Valid EventDto eventDto) {
        log.info("Изменение события с id {}", eventId);
        return adminService.updateEvent(userId, eventId, eventDto);
    }

    @PostMapping("/partners")
    @ResponseStatus(HttpStatus.CREATED)
    public PartnerDto createPartner(@RequestBody @Valid PartnerDto partnerDto) {
        log.info("Добавление нового партнера {}", partnerDto.toString());
        return adminService.createPartner(partnerDto);
    }

    @DeleteMapping("/partners/{partnerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartner(@PathVariable @PositiveOrZero Long partnerId) {
        log.info("Удаление партнера с id {}", partnerId);
        adminService.deletePartner(partnerId);
    }

    @PatchMapping("/partners/{partnerId}")
    @ResponseStatus(HttpStatus.OK)
    public PartnerDto updatePartner(@PathVariable @PositiveOrZero Long partnerId, @RequestBody @Valid PartnerDto partnerDto) {
        log.info("Изменение партнера с id {}", partnerId);
        return adminService.updatePartner(partnerId, partnerDto);
    }

    @PostMapping("/streams")
    @ResponseStatus(HttpStatus.CREATED)
    public StreamDto createStream(@RequestBody @Valid StreamDto streamDto) {
        log.info("Добавление нового стрима {} администратором",
                streamDto.toString());
        return adminService.createStream(streamDto);
    }

    @PatchMapping("/streams/{streamId}")
    @ResponseStatus(HttpStatus.OK)
    public StreamDto updateStream(@PathVariable @PositiveOrZero Long streamId,
                                  @RequestBody @Valid StreamDto streamDto) {
        log.info("Изменение данных стрима с id {} новыми данными {}",
                streamId, streamDto.toString());
        return adminService.updateStream(streamId, streamDto);
    }

    @DeleteMapping("/streams/{streamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStream(@PathVariable @PositiveOrZero Long streamId) {
        log.info("Удаление комментария с id {} ", streamId);
        adminService.deleteStream(streamId);
    }

    @PostMapping("/competition")
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionDto createCompetition(@RequestBody @Valid CompetitionDto competitionDto) {
        log.info("Добавление нового конкурса {}", competitionDto.toString());
        return adminService.createCompetition(competitionDto);
    }

    @DeleteMapping("/competition/{competitionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompetition(@PathVariable @PositiveOrZero Long competitionId) {
        log.info("Удаление конкурса с id {}", competitionId);
        adminService.deleteCompetition(competitionId);
    }

    @PatchMapping("/competition/{competitionId}")
    @ResponseStatus(HttpStatus.OK)
    public CompetitionDto updateCompetition(@PathVariable @PositiveOrZero Long competitionId,
                                            @RequestBody @Valid CompetitionDto competitionDto) {
        log.info("Изменение конкурса с id {}", competitionId);
        return adminService.updateCompetition(competitionId, competitionDto);
    }

    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        return userService.createUser(userDto);
    }

    @DeleteMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @PositiveOrZero Long userId) {
        log.info("Удаление пользователя с id {}", userId);
        userService.deleteUser(userId);
    }
}
