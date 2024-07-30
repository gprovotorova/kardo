package com.kardoaward.kardo.media.service;

import com.kardoaward.kardo.exception.ObjectExistException;
import com.kardoaward.kardo.media.dto.MediaDto;
import com.kardoaward.kardo.media.model.Media;
import com.kardoaward.kardo.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{

    private final MediaRepository mediaRepository;
    private ModelMapper mapper;

    @Override
    public MediaDto createMedia(MultipartFile resource) {
        String name = generateKey(resource.getName());
        MediaDto createdMedia = MediaDto.builder()
                .name(name)
                .build();
        Media media = mapper.map(createdMedia, Media.class);
        if (mediaRepository.existsByName(media.getName())) {
            throw new ObjectExistException("Медиа файл с таким именем уже существует.");
        }
        return mapper.map(mediaRepository.save(media), MediaDto.class);
    }

    private String generateKey(String name) {
        return DigestUtils.md5Hex(name + LocalDateTime.now().toString());
    }
}
