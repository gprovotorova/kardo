package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@Slf4j
@Validated
@Tag(name = "Мероприятия")
public class EventController {

    private EventService eventService;

    @Operation(description = "Получение мероприятий с возможностью фильтрации")
    @GetMapping()
    public List<EventDto> getEventsWithFilters(@RequestParam(required = false) EventType eventType,
                                               @RequestParam(required = false)
                                               @DateTimeFormat(pattern = Constants.DATE_FORMAT) LocalDate date,
                                               @RequestParam(required = false) DirectionType direction,
                                               @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                               @RequestParam(defaultValue = "10") @Positive int size) {
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return eventService.getEvents(eventType, date, direction, page);
    }

    @Operation(description = "Получение мероприятия по его id")
    @GetMapping(path = "/{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }
}
