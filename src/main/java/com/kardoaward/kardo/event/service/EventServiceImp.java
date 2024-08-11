package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.direction.model.Direction;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getEvents(EventType eventType, LocalDate date, DirectionType directionType, Pageable page) {
        List<EventDto> savedEvents = new ArrayList<>();
        if(directionType == null){
            savedEvents = eventRepository.findEvents(eventType, date, page)
                    .stream()
                    .map(event -> mapper.map(event, EventDto.class)).collect(Collectors.toList());
        } else {
            List<Event> eventsDB = eventRepository.findEvents(eventType, date, page)
                    .stream().collect(Collectors.toList());
            for (Event event: eventsDB) {
                Set<Direction> directions = event.getDirection();
                for (Direction direction : directions) {
                    if (direction.getName().equals(directionType)){
                        savedEvents.add(mapper.map(event, EventDto.class));
                    }
                }
            }
        }
        return savedEvents;
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие не найдено или недоступно"));
        return mapper.map(event, EventDto.class);
    }
}
