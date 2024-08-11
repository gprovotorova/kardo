package com.kardoaward.kardo.file.service;

import com.kardoaward.kardo.exception.ObjectNotFoundException;
import com.kardoaward.kardo.file.FileRepository;
import com.kardoaward.kardo.file.manager.FileManager;
import com.kardoaward.kardo.file.model.FileInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileManager fileManager;


    @Override
    @Transactional
    public FileInfo upload(MultipartFile resource) throws IOException {
        String key = generateKey(resource.getName());
        FileInfo createdFile = FileInfo.builder()
                .name(resource.getOriginalFilename())
                .key(key)
                .size(resource.getSize())
                .build();

        FileInfo savedFile = fileRepository.save(createdFile);
        fileManager.upload(resource.getBytes(), key);

        return savedFile;
    }

    @Override
    public Resource download(String key) throws IOException {
        return fileManager.download(key);
    }

    @Transactional(readOnly = true)
    @Override
    public FileInfo findById(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new ObjectNotFoundException("Файл с id = " + fileId + " не найден."));
    }

    @Override
    @Transactional
    public void delete(Long fileId) throws IOException {
        fileRepository.findById(fileId)
                .ifPresent(file -> fileRepository.deleteById(fileId));
    }

    private String generateKey(String name) {
        return DigestUtils.md5Hex(name + LocalDateTime.now().toString());
    }
}

