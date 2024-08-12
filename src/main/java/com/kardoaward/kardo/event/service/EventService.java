package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventDto getEventById(long userId, Long eventId);

    List<EventDto> getEvents(long userId, EventType eventType, LocalDate date, DirectionType direction, Pageable page);
}
