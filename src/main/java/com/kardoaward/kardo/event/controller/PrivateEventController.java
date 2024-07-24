package com.kardoaward.kardo.event.controller;

import com.kardoaward.kardo.event.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Validated
@ResponseStatus
public class PrivateEventController {

    private EventService eventService;

    @PostMapping("event/{userId}/events/{eventId}/request")
    public String getEventsRequests(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getEventsRequests(userId, eventId);
    }
}
