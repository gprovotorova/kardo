package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService{

    private final EventRepository eventRepository;

    private ModelMapper mapper;
    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        if(eventRepository.existsByName(event.getId())){
            throw new ObjectExistException("Событие с таким названием уже существует");
        }
        return mapper.map(eventRepository.save(event), EventDto.class);
    }


    @Override
    public void deleteUser(Long eventId) {
        if(eventRepository.existsById(eventId)){
            eventRepository.deleteById(eventId);
        } else {
            throw new ObjectNotFoundException("События с таким id не существует");
        }
    }

    @Override
    public EventDto updateEvent(Long eventId, EventDto eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        if(!eventRepository.existsById(eventId)){
            throw new ObjectNotFoundException("События с таким id не существует");
        }
        return mapper.map(eventRepository.save(event), EventDto.class);
    }
}
