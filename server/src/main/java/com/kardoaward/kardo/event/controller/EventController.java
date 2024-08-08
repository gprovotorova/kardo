package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EventController {

    private EventService eventService;
    private static final String USER = "Authorization";


    @GetMapping()
    public List<EventDto> getEventsWithFilters(@RequestHeader(USER) long userId,
                                               @RequestParam(required = false) EventType eventType,
                                               @RequestParam(required = false)
                                               @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                               @RequestParam(defaultValue = "false") EnumSet<DirectionType> direction,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                               @RequestParam(defaultValue = "10") @Positive int size) {
        return eventService.getEvents(userId, eventType, date, direction, from, size);
    }

    @GetMapping(path = "/{eventId}")
    public EventDto getEventById(@RequestHeader(USER) long userId, @PathVariable Long eventId) {
        return eventService.getEventById(userId, eventId);
    }
}
