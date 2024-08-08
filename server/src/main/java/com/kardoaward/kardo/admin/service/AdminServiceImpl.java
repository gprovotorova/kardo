package com.kardoaward.kardo.admin.service;

import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.competition.repository.CompetitionRepository;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.event.repository.EventRepository;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.StorageFileNotFoundException;
import com.kardoaward.kardo.file.dao.FileDAO;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.partners.model.Partner;
import com.kardoaward.kardo.partners.repository.PartnerRepository;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.streams.model.Stream;
import com.kardoaward.kardo.streams.repository.StreamRepository;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CompetitionRepository competitionRepository;
    private final PartnerRepository partnerRepository;
    private final EventRepository eventRepository;
    private final StreamRepository streamRepository;
    private final UserRepository userRepository;

    private ModelMapper mapper;

    @Override
    @Transactional
    public EventDto createEvent(Long userId, EventDto eventDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType()!= UserType.ADMIN){
            throw new ConflictDataException("Нет доступа");

        }
        Event event = mapper.map(eventDto, Event.class);
        if(eventRepository.existsByName(event.getId())){
            throw new ObjectExistException("Событие с таким названием уже существует");
        }
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    @Transactional
    public void deleteEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType()!= UserType.ADMIN){
            throw new ConflictDataException("Нет доступа");

        }
        if(eventRepository.existsById(eventId)){
            eventRepository.deleteById(eventId);
        } else {
            throw new ObjectNotFoundException("События с таким id не существует");
        }
    }

    @Override
    @Transactional
    public EventDto updateEvent(Long userId, Long eventId, EventDto eventDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType()!= UserType.ADMIN){
            throw new ConflictDataException("Нет доступа");

        }
        Event event = mapper.map(eventDto, Event.class);
        if(!eventRepository.existsById(eventId)){
            throw new ObjectNotFoundException("События с таким id не существует");
        }
        return mapper.map(eventRepository.save(event), EventDto.class);
    }

    @Override
    @Transactional
    public PartnerDto createPartner(PartnerDto partnerDto) {
        Partner partner = mapper.map(partnerDto, Partner.class);
        if(partnerRepository.existsByName(partner.getName())){
            throw new ObjectExistException("Партнер с таким названием уже существует");
        }
        return mapper.map(partnerRepository.save(partner), PartnerDto.class);
    }

    @Override
    @Transactional
    public void deletePartner(Long partnerId) {
        if(partnerRepository.existsById(partnerId)){
            partnerRepository.deleteById(partnerId);
        } else {
            throw new ObjectNotFoundException("Партнер с таким id не существует");
        }
    }

    @Override
    @Transactional
    public PartnerDto updatePartner(Long partnerId, PartnerDto partnerDto) {
        Partner partner = mapper.map(partnerDto, Partner.class);
        if(!partnerRepository.existsById(partnerId)){
            throw new ObjectNotFoundException("Партнер с таким id не существует");
        }
        return mapper.map(partnerRepository.save(partner), PartnerDto.class);
    }

    @Override
    @Transactional
    public StreamDto createStream(StreamDto streamDto) {
        Stream stream = mapper.map(streamDto, Stream.class);

        return mapper.map(streamRepository.save(stream), StreamDto.class);
    }

    @Override
    @Transactional
    public StreamDto updateStream(Long streamId, StreamDto streamDto) {
        Stream stream = streamRepository.findById(streamId)
                .orElseThrow(() -> new ObjectNotFoundException("Стрим с id = " + streamId + " не найден."));

        stream.setName(streamDto.getName());
        stream.setLink(streamDto.getLink());
        stream.setStreamDateTime(streamDto.getStreamDateTime());
        stream.setPublishedDate(LocalDateTime.now());

        return mapper.map(streamRepository.save(stream), StreamDto.class);
    }

    @Override
    @Transactional
    public void deleteStream(Long streamId) {
        streamRepository.findById(streamId)
                .ifPresent(user -> streamRepository.deleteById(streamId));
    }

    @Override
    @Transactional
    public CompetitionDto createCompetition(CompetitionDto competitionDto) {
        Competition competition = mapper.map(competitionDto,Competition.class);
        if(competitionRepository.existsByName(competition.getName())){
            throw new ObjectExistException("Конкурс с таким названием уже существует");
        }
        return mapper.map(competitionRepository.save(competition), CompetitionDto.class);
    }

    @Override
    @Transactional
    public void deleteCompetition(Long competitionId) {
        if(competitionRepository.existsById(competitionId)){
            competitionRepository.deleteById(competitionId);
        } else {
            throw new ObjectNotFoundException("Конкурса с таким id не существует");
        }
    }

    @Override
    @Transactional
    public CompetitionDto updateCompetition(Long competitionId, CompetitionDto competitionDto) {
        Competition competition = mapper.map(competitionDto,Competition.class);
        if(!competitionRepository.existsById(competitionId)) {
            throw new ObjectNotFoundException("Конкурса с таким id не существует");

        }
        return mapper.map(competitionRepository.save(competition), CompetitionDto.class);
    }
}
