package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventDto> getEvents(EventType eventType, LocalDate date, DirectionType direction, Pageable page);

    EventDto getEventById(Long eventId);
}
