package com.kardoaward.kardo.user.controller;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.competition.dto.CompetitionDto;
import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.event.dto.EventDto;
import com.kardoaward.kardo.file.controller.FileController;
import com.kardoaward.kardo.participant.dto.CompetitionParticipantDto;
import com.kardoaward.kardo.participant.dto.EvaluatingParticipantDto;
import com.kardoaward.kardo.participant.dto.EventParticipantDto;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.dto.UserDto;
import com.kardoaward.kardo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping
@Slf4j
@AllArgsConstructor
@Validated
public class UserController {
    @Autowired
    private final FileController fileController;
    private final UserService userService;

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(@RequestParam(required = false) List<Long> ids,
                                     @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        if (ids == null) {
            ids = new ArrayList<>();
        }
        log.info("Получение всех пользователей: {}", ids.size());
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllUsers(ids, page);
    }

    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        return userService.createUser(userDto);
    }

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto updateUser(@PathVariable @PositiveOrZero Long userId,
                              @RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @PositiveOrZero Long userId) {
        log.info("Удаление пользователя с id {}", userId);
        userService.deleteUser(userId);
    }


    @PostMapping("/users/{userId}/events/{eventId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long eventId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Добавление нового комментария {} пользователем с id {} к событию с id {}",
                commentDto.toString(), userId, eventId);
        return userService.createComment(userId, eventId, commentDto);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable @PositiveOrZero Long userId,
                                    @PathVariable @PositiveOrZero Long eventId,
                                    @PathVariable @PositiveOrZero Long commentId,
                                    @RequestBody @Valid NewCommentDto commentDto) {
        log.info("Изменение комментария с id {} добавленного пользователем с id {} новыми данными {}",
                commentId, userId, commentDto.toString());
        return userService.updateComment(userId, eventId, commentId, commentDto);
    }

    @DeleteMapping("/users/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @PositiveOrZero Long userId,
                              @PathVariable @PositiveOrZero Long commentId) {
        log.info("Удаление комментария с id {} пользователем с id {} ", commentId, userId);
        userService.deleteComment(userId, commentId);
    }

    @PostMapping("/users/{userId}/posts/")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@PathVariable @PositiveOrZero Long userId,
                              @RequestBody @Valid NewPostDto postDto,
                              @RequestBody DirectionType direction,
                              @RequestParam MultipartFile attachment) {
        log.info("Добавление нового поста {} пользователем с id {}", postDto.toString(), userId);
        Long fileId = fileController.uploadPhoto(attachment).getBody().getId();
        return userService.createPost(userId, fileId, direction, postDto);
    }

    @PatchMapping("/users/{userId}/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable @PositiveOrZero Long userId,
                              @PathVariable @PositiveOrZero Long postId,
                              @RequestBody @Valid PostDto postDto) {
        log.info("Изменение поста с id {} добавленного пользователем с id {} новыми данными {}",
                postId, userId, postDto.toString());
        return userService.updatePost(userId, postId, postDto);
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable @PositiveOrZero Long userId,
                           @PathVariable @PositiveOrZero Long postId) {
        log.info("Удаление поста с id {} пользователем с id {} ", postId, userId);
        userService.deletePost(userId, postId);
    }

    @PostMapping("/users/{userId}/posts/{postId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addLike(@PathVariable Long userId, @PathVariable Long postId, @RequestParam String type) {
        log.info("Добавление участником с id {} лайка на пост с id {}", userId, postId);
        return userService.addLikeOrDislike(userId, postId, type);
    }

    @PostMapping("/users/{userId}/posts/{postId}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addDislike(@PathVariable Long userId, @PathVariable Long postId, @RequestParam String type) {
        log.info("Добавление участником с id {} дизлайка на пост с id {}", userId, postId);
        return userService.addLikeOrDislike(userId, postId, type);
    }

    @GetMapping("/users/{userId}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllByPostIdAndUserId(@PathVariable @PositiveOrZero Long userId,
                                                     @PathVariable @PositiveOrZero Long postId,
                                                     @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} к событию с id {}", userId, postId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllByPostIdAndUserId(postId, userId, page);
    }

    @GetMapping("/users/{userId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllByUserId(@PathVariable @PositiveOrZero Long userId,
                                           @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                           @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех комментариев пользователя с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getAllByUserId(userId, page);
    }

    @GetMapping("/users/{userId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getByCommentId(@PathVariable @PositiveOrZero Long userId,
                                     @PathVariable @PositiveOrZero Long commentId) {
        log.info("Получение комментария по его id {} ", commentId);
        return userService.getByCommentId(commentId, userId);
    }

    @PostMapping("/users/{userId}/events/{eventId}/request")
    @ResponseStatus(HttpStatus.CREATED)
    public EventParticipantDto addNewRequestToEventByUser(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Регистрация участника с id {} на мероприятие с id {}", userId, eventId);
        return userService.addNewRequestToEventByUser(userId, eventId);
    }

    @PostMapping("/users/{userId}/competition/{compId}/request")
    @ResponseStatus(HttpStatus.CREATED)
    public CompetitionParticipantDto addNewRequestToCompetitionByUser(@PathVariable Long userId,
                                                                      @PathVariable Long compId,
                                                                      @RequestBody @Valid Set<Direction> directions) {
        log.info("Регистрация участника с id {} на конкурс с id {} по направлениям {]", userId, compId, directions);
        return userService.addNewRequestToCompetitionByUser(userId, compId, directions);
    }

    @GetMapping("/users/{userId}/events/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getEventsRequestsByUserId(@PathVariable @PositiveOrZero Long userId,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех мероприятий, на которые зарегистрирован участник с id {} ", userId);
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return userService.getEventsRequestsByUserId(userId, page);
    }

    @GetMapping("/users/{userId}/competition/requests")
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

    @GetMapping("/users/{userId}/competition/{compId}/participants")
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

    @PatchMapping("/users/{userId}/competition/{compId}/participants/{partId}")
    @ResponseStatus(HttpStatus.OK)
    public EvaluatingParticipantDto evaluateParticipant(@PathVariable @PositiveOrZero Long userId,
                                                        @PathVariable @PositiveOrZero Long compId,
                                                        @PathVariable @PositiveOrZero Long partId,
                                                        @RequestBody @Valid int[] points) {
        log.info("Изменение баллов {} участника с id {} в конкурсе c id {} судьей с id {}",
                points, partId, compId, userId);
        return userService.evaluateParticipant(userId, compId, partId, points);
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration/watcher")
    public String addParticipant(@RequestBody @Valid UserDto userDto, Map<String, Object> model) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.WATCHER);
        userService.createUser(userDto);

        return "redirect:/login";
    }

    @PostMapping("/registration/admin")
    public String addAdmin(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.ADMIN);
        userService.createUser(userDto);

        return "redirect:/login";
    }

    @PostMapping("/registration/expert")
    public String addExpert(@RequestBody @Valid UserDto userDto) {
        log.info("Добавление нового пользователя {}", userDto.toString());
        userDto.setType(UserType.EXPERT);
        userService.createUser(userDto);

        return "redirect:/login";
    }
}
