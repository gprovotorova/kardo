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
    @Transactional(readOnly = true)
    public StreamDto getStreamById(Long streamId) {
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
}
