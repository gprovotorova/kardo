package com.kardoaward.kardo.user.controller;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.file.controller.FileController;
import com.kardoaward.kardo.participant.dto.CompetitionParticipantDto;
import com.kardoaward.kardo.participant.dto.EvaluatingParticipantDto;
import com.kardoaward.kardo.participant.dto.EventParticipantDto;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.dto.NewUserDto;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name="Пользователи")
public class UserController {
    private final FileController fileController;
    private final UserService userService;

    @Operation(description = "Обновление данных пользователя")
    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable @PositiveOrZero Long userId,
                              @RequestBody @Valid NewUserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        return userService.updateUser(userId, userDto);
    }

    @Operation(description = "Создание комментария")
    @PostMapping("/{userId}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long postId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Добавление нового комментария {} пользователем с id {} к событию с id {}",
                commentDto.toString(), userId, postId);
        return userService.createComment(userId, postId, commentDto);
    }

    @Operation(description = "Обновление данных комментария")
    @PatchMapping("/{userId}/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long postId,
                                    @PathVariable @PositiveOrZero Long commentId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Изменение комментария с id {} добавленного пользователем с id {} новыми данными {}",
                commentId, userId, commentDto.toString());
        return userService.updateComment(userId, postId, commentId, commentDto);
    }

    @Operation(description = "Удаление комментария по id")
    @DeleteMapping("/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @PositiveOrZero Long userId,
                              @PathVariable @PositiveOrZero Long commentId) {
        log.info("Удаление комментария с id {} пользователем с id {} ", commentId, userId);
        userService.deleteComment(userId, commentId);
    }

    @Operation(description = "Создание поста")
    @PostMapping("/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@PathVariable @PositiveOrZero Long userId,
                              @RequestBody @Valid NewPostDto postDto,
                              @RequestBody DirectionType direction,
                              @RequestParam MultipartFile attachment) {
        log.info("Добавление нового поста {} пользователем с id {}", postDto.toString(), userId);
        Long fileId = fileController.uploadPhoto(attachment).getBody().getId();
        return userService.createPost(userId, fileId, direction, postDto);
    }

    @Operation(description = "Обновление данных поста")
    @PatchMapping("/{userId}/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable @PositiveOrZero Long userId,
                              @PathVariable @PositiveOrZero Long postId,
                              @RequestBody @Valid PostDto postDto) {
        log.info("Изменение поста с id {} добавленного пользователем с id {} новыми данными {}",
                postId, userId, postDto.toString());
        return userService.updatePost(userId, postId, postDto);
    }

    @Operation(description = "Удаление поста по id")
    @DeleteMapping("/{userId}/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable @PositiveOrZero Long userId,
                           @PathVariable @PositiveOrZero Long postId) {
        log.info("Удаление поста с id {} пользователем с id {} ", postId, userId);
        userService.deletePost(userId, postId);
    }

    @Operation(description = "Получение всех постов пользователя с id")
    @GetMapping("/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PostDto> getAllPostsByUserId(@PathVariable @PositiveOrZero Long userId,
                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех постов пользователя с id {}", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllPostsByUserId(userId, page);
    }

    @Operation(description = "Добавление лайка")
    @PostMapping("/{userId}/posts/{postId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addLike(@PathVariable Long userId, @PathVariable Long postId, @RequestParam String type) {
        log.info("Добавление участником с id {} лайка на пост с id {}", userId, postId);
        return userService.addLikeOrDislike(userId, postId, type);
    }

    @Operation(description = "Добавление дизлайка")
    @PostMapping("/{userId}/posts/{postId}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addDislike(@PathVariable Long userId, @PathVariable Long postId, @RequestParam String type) {
        log.info("Добавление участником с id {} дизлайка на пост с id {}", userId, postId);
        return userService.addLikeOrDislike(userId, postId, type);
    }

    @Operation(description = "Получение всех комментариев к событию")
    @GetMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllCommentsByPostId(@PathVariable @PositiveOrZero Long postId,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев к событию с id {}", postId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllCommentsByPostId(postId, page);
    }

    @Operation(description = "Получение всех комментариев пользователя")
    @GetMapping("/{userId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllCommentsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllCommentsByUserId(userId, page);
    }

    @Operation(description = "Получение комментария по его id")
    @GetMapping("/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getByCommentId(@PathVariable @PositiveOrZero Long userId,
                                     @PathVariable @PositiveOrZero Long commentId) {
        log.info("Получение комментария по его id {} ", commentId);
        return userService.getByCommentId(commentId, userId);
    }

    @Operation(description = "Регистрация участника на мероприятие")
    @PostMapping("/{userId}/events/{eventId}/request")
    @ResponseStatus(HttpStatus.CREATED)
    public EventParticipantDto addNewRequestToEventByUser(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Регистрация участника с id {} на мероприятие с id {}", userId, eventId);
        return userService.addNewRequestToEventByUser(userId, eventId);
    }

    @Operation(description = "Регистрация участника на конкурс по направлениям")
    @PostMapping("/{userId}/competition/{compId}/request")
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionParticipantDto addNewRequestToCompetitionByUser(@PathVariable Long userId,
                                                                      @PathVariable Long compId,
                                                                      @RequestBody @Valid Set<Direction> directions) {
        log.info("Регистрация участника с id {} на конкурс с id {} по направлениям {]", userId, compId, directions);
        return userService.addNewRequestToCompetitionByUser(userId, compId, directions);
    }

    @Operation(description = "Получение всех мероприятий, на которые зарегистрирован участник")
    @GetMapping("/{userId}/events/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getEventsRequestsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех мероприятий, на которые зарегистрирован участник с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getEventsRequestsByUserId(userId, page);
    }

    @Operation(description = "Получение всех конкурсов, на которые зарегистрирован участник")
    @GetMapping("/{userId}/competition/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<CompetitionDto> getCompetitionsRequestsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                                @PositiveOrZero
                                                                @RequestParam(defaultValue = "0") Integer from,
                                                                @Positive
                                                                @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех конкурсов, на которые зарегистрирован участник с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getCompetitionsRequestsByUserId(userId, page);
    }

    @Operation(description = "Получение всех участников конкурса судьей для оценки")
    @GetMapping("/{userId}/competition/{compId}/participants")
    @ResponseStatus(HttpStatus.OK)
    public List<CompetitionParticipantDto> getParticipantsByCompetitionIdAndUserId(
            @PathVariable @PositiveOrZero Long userId,
            @PathVariable @PositiveOrZero Long compId,
            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
            @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех участников конкурса c id {} судьей с id {} для оценки", compId, userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getParticipantsByCompetitionIdAndUserId(userId, compId, page);
    }

    @Operation(description = "Изменение баллов участника в конкурсе судьей")
    @PatchMapping("/{userId}/competition/{compId}/participants/{partId}")
    @ResponseStatus(HttpStatus.OK)
    public EvaluatingParticipantDto evaluateParticipant(@PathVariable @PositiveOrZero Long userId,
                                                        @PathVariable @PositiveOrZero Long compId,
                                                        @PathVariable @PositiveOrZero Long partId,
                                                        @RequestBody @Valid int[] points) {
        log.info("Изменение баллов {} участника с id {} в конкурсе c id {} судьей с id {}",
                points, partId, compId, userId);
        return userService.evaluateParticipant(userId, compId, partId, points);
    }
}
