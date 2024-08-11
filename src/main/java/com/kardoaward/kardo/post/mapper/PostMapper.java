package com.kardoaward.kardo.post.mapper;

import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.post.dto.NewPostDto;
import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;

import java.time.LocalDateTime;

public class PostMapper {
    public static Post toPost(NewPostDto newPostDto,
                              FileInfo fileInfo,
                              User user,
                              LocalDateTime created,
                              DirectionType direction) {
        return Post.builder()
                .file(fileInfo)
                .description(newPostDto.getDescription())
                .likes(0L)
                .dislikes(0L)
                .author(user)
                .createdDate(created)
                .updatedDate(LocalDateTime.now())
                .direction(direction)
                .build();
    }
}