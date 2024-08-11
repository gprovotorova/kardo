package com.kardoaward.kardo.file;

import com.kardoaward.kardo.file.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<FileInfo, Long>  {

}
