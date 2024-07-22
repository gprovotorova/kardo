package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.event.dto.EventDto;

public interface EventService {
    EventDto createEvent(EventDto eventDto);

    void deleteUser(Long eventId);

    EventDto updateEvent(Long eventId, EventDto eventDto);
}
