package com.kardoaward.kardo.comment.mapper;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.photo.Photo;
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
        return Comment.builder()
                .text(commentDto.getText())
                .photo(photo)
                .author(user)
                .created(created)
                .build();
    }

    public static Comment toComment(NewCommentDto newCommentDto, Photo photo, User user, LocalDateTime created) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .photo(photo)
                .author(user)
                .created(created)
                .build();
    }

}
