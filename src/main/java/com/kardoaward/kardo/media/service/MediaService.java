package com.kardoaward.kardo.media.service;

import com.kardoaward.kardo.media.dto.MediaDto;
import com.kardoaward.kardo.user.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    MediaDto createMedia(MultipartFile resource);
}
