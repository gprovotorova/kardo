package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.direction.DirectionDto;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.EventClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/events")
@Slf4j
@AllArgsConstructor
@Validated
public class EventController {

    private EventClient eventClient;

    @GetMapping()
    public ResponseEntity getEventsWithFilters(@RequestParam(required = false) String text,
                                               @RequestParam(required = false) EventType eventType,
                                               @RequestParam(required = false)
                                               @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                               @RequestParam(defaultValue = "false") Set<DirectionDto> direction,
                                               @RequestParam(required = false) String sort,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                               @RequestParam(defaultValue = "10") @Positive Integer size) {
        return eventClient.getEventsWithFilters(text, eventType, date, direction, sort, from, size);
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity getEventById(@PathVariable Long eventId) {
        return eventClient.getEventById(eventId);
    }
}
