package com.kardoaward.kardo.streams.service;

import com.kardoaward.kardo.streams.dto.StreamDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface StreamService {

    StreamDto updateStream(Long userId, StreamDto streamDto);

    StreamDto getByStreamId(Long streamId);

    List<StreamDto> getAllStreams(Pageable page);

    List<StreamDto> getStreamsWithFilters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable page);

    StreamDto createStream(StreamDto streamDto);

    void deleteStream(Long streamId);

}
