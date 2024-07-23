package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventDto createEvent(EventDto eventDto);

    void deleteUser(Long eventId);

    EventDto updateEvent(Long eventId, EventDto eventDto);

    List<EventDto> getEvents(String text, EventType eventType, LocalDateTime date, Direction direction, String sort, int from, int size);

    EventDto getEvent(long id);

    String getEventsRequests(long userId, long eventId);
}
