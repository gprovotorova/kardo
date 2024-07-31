package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService{

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        if(eventRepository.existsByName(event.getId())){
            throw new ObjectExistException("Событие с таким названием уже существует");
        }
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    public void deleteEvent(Long eventId) {
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

    @Override
    public List<EventDto> getEvents(String text, EventType eventType, LocalDateTime date, Direction direction, String sort, int from, int size) {
        List<EventDto> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        if (date == null) {
            date = now;
        }
        return result;
    }
    @Override
    public EventDto getEvent(long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Событие не найдено или недоступно"));
        return mapper.map(event,EventDto.class);
    }

    @Override
    public String getEventsRequests(long userId, long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("Событие не найдено или недоступно"));
        User user = userRepository.findById(eventId).orElseThrow(() -> new ObjectNotFoundException("Пользователь не найден"));
        //TODO проверить является ли user участником конкурса, если нет, предложить зарегистрироваться
        return "Запрос принят";
    }

}
