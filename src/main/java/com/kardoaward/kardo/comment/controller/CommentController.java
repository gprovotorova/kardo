package com.kardoaward.kardo.comment.controller;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.service.CommentService;
import com.kardoaward.kardo.common.PageMaker;
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
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/users/{userId}/events/{eventId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long eventId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Добавление нового комментария {} пользователем с id {} к событию с id {}",
                commentDto.toString(), userId, eventId);
        return commentService.createComment(userId, eventId, commentDto);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long eventId,
                                    @PathVariable @PositiveOrZero Long commentId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Изменение комментария с id {} добавленного пользователем с id {} новыми данными {}",
                commentId, userId, commentDto.toString());
        return commentService.updateComment(userId, eventId, commentId, commentDto);
    }

    @DeleteMapping("/users/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByUser(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long commentId) {
        log.info("Удаление комментария с id {} пользователем с id {} ", commentId, userId);
        commentService.deleteCommentByUser(userId, commentId);
    }

    @GetMapping("/users/{userId}/events/{eventId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllByEventIdAndUserId(@PathVariable @PositiveOrZero Long userId,
                                                     @PathVariable @PositiveOrZero Long eventId,
                                                     @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} к событию с id {}", userId, eventId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return commentService.getAllByEventIdAndUserId(eventId, userId, page);
    }

    @GetMapping("/users/{userId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllByUserId(@PathVariable @PositiveOrZero Long userId,
                                           @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                           @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return commentService.getAllByUserId(userId, page);
    }

    @GetMapping("/users/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getByCommentId(@PathVariable @PositiveOrZero Long userId,
                                     @PathVariable @PositiveOrZero Long commentId) {
        log.info("Получение комментария по его id {} ", commentId);
        return commentService.getByCommentId(commentId, userId);
    }

    @GetMapping("/events/{eventId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getByEventId(@PathVariable @PositiveOrZero Long eventId,
                                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев к событию с id {} ", eventId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return commentService.getByEventId(eventId, page);
    }

    @GetMapping("/admin/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                           @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return commentService.getAllComments(page);
    }


    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsWithFilters(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(required = false) LocalDateTime rangeStart,
                                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   @RequestParam(required = false) LocalDateTime rangeEnd,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение комментариев с возможностью фильтрации");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return commentService.getCommentsWithFilters(rangeStart, rangeEnd, page);
    }
}
