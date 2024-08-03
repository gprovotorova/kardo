package com.kardoaward.kardo.streams.controller;

import com.kardoaward.kardo.streams.StreamClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/streams")
@RequiredArgsConstructor
@Slf4j
@Validated
public class StreamController {

    private final StreamClient streamClient;

    @GetMapping("/{streamId}")
    public ResponseEntity getStreamById(@PathVariable @PositiveOrZero Long streamId) {
        log.info("Получение стрима по его id {} ", streamId);
        return streamClient.getStreamById(streamId);
    }

    @GetMapping
    public ResponseEntity getAllStreams(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех стримов");
        return streamClient.getAllStreams(from, size);
    }
}
