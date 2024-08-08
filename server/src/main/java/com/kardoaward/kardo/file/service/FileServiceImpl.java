package com.kardoaward.kardo.file.service;

import com.kardoaward.kardo.file.dao.FileDAO;
import com.kardoaward.kardo.file.manager.FileManager;
import com.kardoaward.kardo.file.model.FileInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;
    private final FileManager fileManager;

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public FileInfo upload(MultipartFile resource) throws IOException {
        String key = generateKey(resource.getName());
        FileInfo createdFile = FileInfo.builder()
                .name(resource.getOriginalFilename())
                .size(resource.getSize())
                .key(key)
                .build();
        createdFile = fileDAO.create(createdFile);
        Path path = fileManager.upload(resource.getBytes(), key);
        createdFile.setPath(path.toString());
        return createdFile;
    }

    @Override
    public Resource download(String key) throws IOException {
        return fileManager.download(key);
    }

    @Transactional(readOnly = true)
    @Override
    public FileInfo findById(Long fileId) {
        return fileDAO.findById(fileId);
    }

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public void delete(Long fileId) throws IOException {
        FileInfo file = fileDAO.findById(fileId);
        fileDAO.delete(fileId);
        fileManager.delete(file.getKey());
    }

    private String generateKey(String name) {
        return DigestUtils.md5Hex(name + LocalDateTime.now().toString());
    }
}

