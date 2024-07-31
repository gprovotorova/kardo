package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping
@Slf4j
@Validated
public class AdminEventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/admin/events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent (@RequestBody @Valid EventDto eventDto){
        log.info("Добавление нового события {}", eventDto.toString());
        return eventService.createEvent(eventDto);
    }

    @DeleteMapping("/admin/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable @PositiveOrZero Long eventId) {
        log.info("Удаление события с id {}", eventId);
        eventService.deleteEvent(eventId);
    }

    @PatchMapping("/admin/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEvent(@PathVariable @PositiveOrZero Long eventId, @RequestBody @Valid EventDto eventDto){
        log.info("Изменение события с id {}", eventId);
        return eventService.updateEvent(eventId, eventDto);
    }

}
