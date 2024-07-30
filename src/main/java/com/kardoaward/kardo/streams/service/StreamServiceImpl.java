package com.kardoaward.kardo.streams.service;

import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.ObjectValidationException;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.streams.model.Stream;
import com.kardoaward.kardo.streams.repository.StreamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.kardoaward.kardo.common.Constants.MAX_DATE;


@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;
    private ModelMapper mapper;

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
    @Transactional(readOnly = true)
    public StreamDto getByStreamId(Long streamId) {
        Stream stream = streamRepository.findById(streamId)
                .orElseThrow(() -> new ObjectNotFoundException("Стрим с id = " + streamId + " не найден."));
        return mapper.map(stream, StreamDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StreamDto> getAllStreams(Pageable page) {
        return streamRepository.findAll(page)
                .getContent()
                .stream()
                .map(stream -> mapper.map(stream, StreamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StreamDto> getStreamsWithFilters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable page) {
        if (rangeStart != null && rangeEnd != null && rangeStart.isAfter(rangeEnd)) {
            throw new ObjectValidationException("Дата начала сортировки не может быть позже даты конца.");
        }

        if (rangeStart == null) rangeStart = LocalDateTime.now();
        if (rangeEnd == null) rangeEnd = MAX_DATE;

        List<StreamDto> streams = streamRepository.getStreamsWithFilters(rangeStart, rangeEnd, page)
                .getContent().stream()
                .map(stream -> mapper.map(stream, StreamDto.class))
                .collect(Collectors.toList());
        return streams;
    }
}
