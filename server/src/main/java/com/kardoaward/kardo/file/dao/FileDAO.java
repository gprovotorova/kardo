package com.kardoaward.kardo.file.dao;

import com.kardoaward.kardo.file.model.FileInfo;

public interface FileDAO {

    FileInfo create(FileInfo file);

    FileInfo findById(Long fileId);

    void delete(Long fileId);
}
