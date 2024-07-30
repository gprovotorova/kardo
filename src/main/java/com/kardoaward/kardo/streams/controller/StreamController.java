package com.kardoaward.kardo.streams.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.streams.dto.StreamDto;
import com.kardoaward.kardo.streams.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Validated
public class StreamController {

    private final StreamService streamService;

    @PostMapping("/admin/streams")
    @ResponseStatus(HttpStatus.CREATED)
    public StreamDto createStream(@RequestBody @Valid StreamDto streamDto) {
        log.info("Добавление нового стрима {} администратором",
                streamDto.toString());
        return streamService.createStream(streamDto);
    }

    @PatchMapping("/admin/streams/{streamId}")
    @ResponseStatus(HttpStatus.OK)
    public StreamDto updateStream(@PathVariable @PositiveOrZero Long streamId,
                                    @RequestBody @Valid StreamDto streamDto) {
        log.info("Изменение данных стрима с id {} новыми данными {}",
                streamId, streamDto.toString());
        return streamService.updateStream(streamId, streamDto);
    }

    @DeleteMapping("/admin/streams/{streamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStreamByUser(@PathVariable @PositiveOrZero Long streamId) {
        log.info("Удаление комментария с id {} ", streamId);
        streamService.deleteStream(streamId);
    }

    @GetMapping("/news/streams/{streamId}")
    @ResponseStatus(HttpStatus.OK)
    public StreamDto getByStreamId(@PathVariable @PositiveOrZero Long streamId) {
        log.info("Получение стрима по его id {} ", streamId);
        return streamService.getByStreamId(streamId);
    }

    @GetMapping("/news/streams")
    @ResponseStatus(HttpStatus.OK)
    public List<StreamDto> getAllStreams(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех стримов");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return streamService.getAllStreams(page);
    }


    @GetMapping("/news/streams")
    @ResponseStatus(HttpStatus.OK)
    public List<StreamDto> getStreamsWithFilters(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                 @RequestParam(required = false) LocalDateTime rangeStart,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                 @RequestParam(required = false) LocalDateTime rangeEnd,
                                                 @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                 @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение стримов с возможностью фильтрации");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return streamService.getStreamsWithFilters(rangeStart, rangeEnd, page);
    }
}
