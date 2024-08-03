package com.kardoaward.kardo.post.controller;

import com.kardoaward.kardo.post.PostClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PostController {

    private final PostClient postClient;

    @GetMapping("/{postId}")
    public ResponseEntity getPostById(@PathVariable @PositiveOrZero Long postId) {
        return postClient.getPostById(postId);
    }

    @GetMapping
    public ResponseEntity getAllPosts(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                      @Positive @RequestParam(defaultValue = "10") Integer size) {
        return postClient.getAllPosts(from, size);
    }
}
