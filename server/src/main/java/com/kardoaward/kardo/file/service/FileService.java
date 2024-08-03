package com.kardoaward.kardo.file.service;

import com.kardoaward.kardo.file.model.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileInfo upload(MultipartFile resource) throws IOException;

    Resource download(String key) throws IOException;

    FileInfo findById(Long fileId);

    void delete(Long fileId) throws IOException;
}