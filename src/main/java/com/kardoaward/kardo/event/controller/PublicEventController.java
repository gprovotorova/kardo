package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Validated
public class PublicEventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public List<EventDto> getEvents(@RequestParam(required = false) String text,
                                    @RequestParam(required = false) EventType eventType,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                    @RequestParam(defaultValue = "false") Direction direction,
                                    @RequestParam(required = false) String sort,
                                    @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                    @RequestParam(defaultValue = "10") @Positive int size) {
        return eventService.getEvents(text, eventType, date, direction, sort, from, size);
    }

    @GetMapping(path = "/{id}")
    public EventDto getEvent(@PathVariable long id) {
        return eventService.getEvent(id);
    }
}
