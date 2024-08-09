package com.kardoaward.kardo.streams.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.partners.dto.PartnerDto;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.streams.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/streams")
@RequiredArgsConstructor
@Slf4j
@Validated
public class StreamController {

    private final StreamService streamService;
    private static final String USER = "Authorization";

    @GetMapping("/{streamId}")
    @ResponseStatus(HttpStatus.OK)
    public StreamDto getStreamById(@RequestHeader(USER) long userId,
                                   @PathVariable @PositiveOrZero Long streamId) {
        log.info("Получение стрима по его id {} ", streamId);
        return streamService.getStreamById(userId, streamId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StreamDto> getAllStreams(@RequestHeader(USER) long userId,
                                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех стримов");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return streamService.getAllStreams(userId, page);
    }
}
