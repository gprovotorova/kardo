package com.kardoaward.kardo.user.controller;

import com.kardoaward.kardo.comment.NewCommentDto;
import com.kardoaward.kardo.direction.DirectionDto;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.UserClient;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class UserController {

    private final UserClient userClient;

    @PostMapping("/users/{userId}")
    public ResponseEntity updateUser(@PathVariable @PositiveOrZero Long userId,
                                     @RequestBody @Valid UserDto userDto) {
        log.info("Обновление данных пользователя {}", userDto.toString());
        return userClient.updateUser(userId, userDto);
    }

    @PostMapping("/users/{userId}/events/{eventId}/comments")
    public ResponseEntity createComment(@PathVariable @PositiveOrZero Long userId,
                                        @PathVariable @PositiveOrZero Long eventId,
                                        @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Добавление нового комментария {} пользователем с id {} к событию с id {}",
                commentDto.toString(), userId, eventId);
        return userClient.createComment(userId, eventId, commentDto);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable @PositiveOrZero Long userId,
                                        @PathVariable @PositiveOrZero Long eventId,
                                        @PathVariable @PositiveOrZero Long commentId,
                                        @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Изменение комментария с id {} добавленного пользователем с id {} новыми данными {}",
                commentId, userId, commentDto.toString());
        return userClient.updateComment(userId, eventId, commentId, commentDto);
    }

    @DeleteMapping("/users/{userId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable @PositiveOrZero Long userId,
                                        @PathVariable @PositiveOrZero Long commentId) {
        log.info("Удаление комментария с id {} пользователем с id {} ", commentId, userId);
        return userClient.deleteComment(userId, commentId);
    }

    @PostMapping("/users/{userId}/posts/")
    public ResponseEntity createPost(@PathVariable @PositiveOrZero Long userId,
                                     @RequestBody @Valid NewPostDto postDto) {
        log.info("Добавление нового поста {} пользователем с id {}", postDto.toString(), userId);
        return userClient.createPost(userId, postDto);
    }

    @PatchMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable @PositiveOrZero Long userId,
                                     @PathVariable @PositiveOrZero Long postId,
                                     @RequestBody @Valid PostDto postDto) {
        log.info("Изменение поста с id {} добавленного пользователем с id {} новыми данными {}",
                postId, userId, postDto.toString());
        return userClient.updatePost(userId, postId, postDto);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable @PositiveOrZero Long userId,
                                     @PathVariable @PositiveOrZero Long postId) {
        log.info("Удаление поста с id {} пользователем с id {} ", postId, userId);
        return userClient.deletePost(userId, postId);
    }

    @PostMapping("/users/{userId}/posts/{postId}/like")
    public ResponseEntity addLike(@PathVariable Long userId,
                                  @PathVariable Long postId,
                                  @RequestParam String type) {
        return userClient.addLike(userId, postId, type);
    }

    @PostMapping("/users/{userId}/posts/{postId}/dislike")
    public ResponseEntity addDislike(@PathVariable Long userId,
                                     @PathVariable Long postId,
                                     @RequestParam String type) {
        return userClient.addDislike(userId, postId, type);
    }

    @GetMapping("/users/{userId}/posts/{eventId}/comments")
    public ResponseEntity getAllByPostIdAndUserId(@PathVariable @PositiveOrZero Long userId,
                                                  @PathVariable @PositiveOrZero Long postId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} к событию с id {}", userId, postId);
        return userClient.getAllByPostIdAndUserId(postId, userId, from, size);
    }

    @GetMapping("/users/{userId}/comments")
    public ResponseEntity getAllByUserId(@PathVariable @PositiveOrZero Long userId,
                                         @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                         @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} ", userId);
        return userClient.getAllByUserId(userId, from, size);
    }

    @GetMapping("/users/{userId}/comments/{commentId}")
    public ResponseEntity getCommentById(@PathVariable @PositiveOrZero Long userId,
                                         @PathVariable @PositiveOrZero Long commentId) {
        log.info("Получение комментария по его id {} ", commentId);
        return userClient.getCommentById(commentId, userId);
    }

    @PostMapping("/users/{userId}/events/{eventId}/request")
    public ResponseEntity addNewRequestToEventByUser(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Регистрация участника с id {} на мероприятие с id {}", userId, eventId);
        return userClient.addNewRequestToEventByUser(userId, eventId);
    }

    @PostMapping("/users/{userId}/competition/{compId}/request")
    public ResponseEntity addNewRequestToCompetitionByUser(@PathVariable Long userId,
                                                           @PathVariable Long compId,
                                                           @RequestBody @Valid Set<DirectionDto> directions) {
        log.info("Регистрация участника с id {} на конкурс с id {} по направлениям {]", userId, compId, directions);
        return userClient.addNewRequestToCompetitionByUser(userId, compId, directions);
    }

    @GetMapping("/users/{userId}/events/requests")
    public ResponseEntity getEventsRequestsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех мероприятий, на которые зарегистрирован участник с id {} ", userId);
        return userClient.getEventsRequestsByUserId(userId, from, size);
    }

    @GetMapping("/users/{userId}/competition/requests")
    public ResponseEntity getCompetitionsRequestsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                          @PositiveOrZero
                                                          @RequestParam(defaultValue = "0") Integer from,
                                                          @Positive
                                                          @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех конкурсов, на которые зарегистрирован участник с id {} ", userId);
        return userClient.getCompetitionsRequestsByUserId(userId, from, size);
    }

    @GetMapping("/users/{userId}/competition/{compId}/participants")
    public ResponseEntity getParticipantsByCompetitionIdAndUserId(@PathVariable @PositiveOrZero Long userId,
                                                                  @PathVariable @PositiveOrZero Long compId,
                                                                  @PositiveOrZero
                                                                  @RequestParam(defaultValue = "0") Integer from,
                                                                  @Positive
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех участников конкурса c id {} судьей с id {} для оценки", compId, userId);
        return userClient.getParticipantsByCompetitionIdAndUserId(userId, compId, from, size);
    }

    @PatchMapping("/users/{userId}/competition/{compId}/participants/{partId}")
    public ResponseEntity evaluateParticipant(@PathVariable @PositiveOrZero Long userId,
                                              @PathVariable @PositiveOrZero Long compId,
                                              @PathVariable @PositiveOrZero Long partId,
                                              @RequestBody @Valid int[] points) {
        log.info("Изменение баллов {} участника с id {} в конкурсе c id {} судьей с id {}",
                points, partId, compId, userId);
        return userClient.evaluateParticipant(userId, compId, partId, points);
    }
}
