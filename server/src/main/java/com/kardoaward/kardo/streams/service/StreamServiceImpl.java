package com.kardoaward.kardo.streams.service;

import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.exception.ConflictDataException;
import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.exception.ObjectValidationException;
import com.kardoaward.kardo.exception.StorageFileNotFoundException;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.streams.model.Stream;
import com.kardoaward.kardo.streams.repository.StreamRepository;
import com.kardoaward.kardo.user.model.User;
import com.kardoaward.kardo.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public StreamDto getStreamById(Long userId, Long streamId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType().equals(UserType.WATCHER)){
            throw new ConflictDataException("Нет доступа");

        }
        Stream stream = streamRepository.findById(streamId)
                .orElseThrow(() -> new ObjectNotFoundException("Стрим с id = " + streamId + " не найден."));
        return mapper.map(stream, StreamDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StreamDto> getAllStreams(Long userId, Pageable page) {
        User user = userRepository.findById(userId).orElseThrow(()-> new StorageFileNotFoundException("Такого пользователя не существует"));
        if (user.getType().equals(UserType.WATCHER)){
            throw new ConflictDataException("Нет доступа");

        }
        return streamRepository.findAll(page)
                .getContent()
                .stream()
                .map(stream -> mapper.map(stream, StreamDto.class))
                .collect(Collectors.toList());
    }
}
