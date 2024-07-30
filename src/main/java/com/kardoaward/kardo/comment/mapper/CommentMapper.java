package com.kardoaward.kardo.comment.mapper;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.media.model.Media;
import com.kardoaward.kardo.user.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment, Media media, User user) {
        return CommentDto.builder()
                .photoId(media.getId())
                .text(comment.getText())
                .build();
    }

    public static Comment toComment(CommentDto commentDto, Media media, User user, LocalDateTime created) {
        return Comment.builder()
                .text(commentDto.getText())
                .media(media)
                .author(user)
                .created(created)
                .build();
    }

    public static Comment toComment(NewCommentDto newCommentDto, Media media, User user, LocalDateTime created) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .media(media)
                .author(user)
                .created(created)
                .build();
    }

}
