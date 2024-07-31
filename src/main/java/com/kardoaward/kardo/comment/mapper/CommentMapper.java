package com.kardoaward.kardo.comment.mapper;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.photo.entity.Photo;
import com.kardoaward.kardo.user.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment, Photo photo, User user) {
        return CommentDto.builder()
                .photoId(photo.getId())
                .text(comment.getText())
                .build();
    }

    public static Comment toComment(CommentDto commentDto, Photo photo, User user, LocalDateTime created) {
        return new Comment(commentDto.getText(), photo, user, created);
    }

    public static Comment toComment(NewCommentDto newCommentDto, Photo photo, User user, LocalDateTime created) {
        return new Comment(newCommentDto.getText(), photo, user, created);
    }

}
