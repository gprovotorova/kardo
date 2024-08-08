package com.kardoaward.kardo.event.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.mapper.CommentMapper;
import com.kardoaward.kardo.comment.repository.CommentRepository;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.StorageFileNotFoundException;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    public List<EventDto> getEvents(Long userId, EventType eventType, LocalDateTime date, EnumSet<DirectionType> direction, int from, int size) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType().equals(UserType.WATCHER)){
            throw new ConflictDataException("Нет доступа");
        }
        List<EventDto> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        if (date == null) {
            date = now;
        }
        return result;
    }
    @Override
    public EventDto getEventById(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType().equals(UserType.WATCHER)){
            throw new ConflictDataException("Нет доступа");
        }
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ObjectNotFoundException("Событие не найдено или недоступно"));
        return mapper.map(event,EventDto.class);
    }
}
