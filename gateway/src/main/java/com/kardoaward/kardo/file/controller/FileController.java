package com.kardoaward.kardo.file.controller;

import com.kardoaward.kardo.file.FileClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media/storage")
@Slf4j
@AllArgsConstructor
@Validated
public class FileController {

    private final FileClient fileClient;

    @PostMapping()
    public ResponseEntity<Object> uploadPhoto(@RequestParam MultipartFile attachment) {
        log.info("Загрузка нового файла");
            return fileClient.upload(attachment);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> downloadPhoto(@PathVariable("id") Long id) {
        log.info("Выгрузка файла по его id {}", id);
        return fileClient.download(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePhoto(@PathVariable("id") Long id) {
        log.info("Удаление файла по его id {}", id);
        return fileClient.delete(id);
    }
}
