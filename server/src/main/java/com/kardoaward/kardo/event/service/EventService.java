package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

public interface EventService {

    List<EventDto> getEvents(EventType eventType, LocalDateTime date, EnumSet<DirectionType> direction, int from, int size);

    EventDto getEventById(Long eventId);

    List<CommentDto> getCommentsByEventId(Long eventId, Pageable page);
}
