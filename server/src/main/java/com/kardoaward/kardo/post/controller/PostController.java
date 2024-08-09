package com.kardoaward.kardo.post.controller;

import com.kardoaward.kardo.common.PageMaker;
import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController("PostClassController")
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostController {

    private final PostService postService;
    private static final String USER = "Authorization";

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@RequestHeader(USER) long userId, @PathVariable @PositiveOrZero Long postId) {
        log.info("Получение поста по его id {} ", postId);
        return postService.getPostById(userId, postId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех постов");
        Pageable page = PageMaker.makePageableWithSort(from, size);
        return postService.getAllPosts(page);
    }
}
