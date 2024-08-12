package com.kardoaward.kardo.streams.service;

import com.kardoaward.kardo.streams.dto.StreamDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface StreamService {

    StreamDto getStreamById(Long userId, Long streamId);

    List<StreamDto> getAllStreams(Pageable page);
}
