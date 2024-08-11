package com.kardoaward.kardo.comment.mapper;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment, Post post, User user) {
        return CommentDto.builder()
                .postId(post.getId())
                .userId(user.getId())
                .text(comment.getText())
                .build();
    }

    public static Comment toComment(NewCommentDto newCommentDto, Post post, User user, LocalDateTime created) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .post(post)
                .author(user)
                .createdDate(created)
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
