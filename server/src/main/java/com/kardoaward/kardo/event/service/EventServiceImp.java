package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.mapper.CommentMapper;
import com.kardoaward.kardo.comment.repository.CommentRepository;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService{

    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;
    private ModelMapper mapper;

    @Override
    public List<EventDto> getEvents(EventType eventType, LocalDateTime date, EnumSet<DirectionType> direction, int from, int size) {
        List<EventDto> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        if (date == null) {
            date = now;
        }
        return result;
    }
    @Override
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие не найдено или недоступно"));
        return mapper.map(event,EventDto.class);
    }
}
