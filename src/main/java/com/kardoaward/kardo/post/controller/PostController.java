package com.kardoaward.kardo.post.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name="Посты")
public class PostController {

    private static final String USER = "Authorization";
    private final PostService postService;

    @Operation(description = "Получение поста по его id")
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@RequestHeader(USER) long userId, @PathVariable @PositiveOrZero Long postId) {
        log.info("Получение поста по его id {} ", postId);
        return postService.getPostById(userId, postId);
    }

    @Operation(description = "Получение всех постов")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех постов");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return postService.getAllPosts(page);
    }
}
