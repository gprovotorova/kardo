package com.kardoaward.kardo.comment.mapper;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.event.mapper.EventMapper;
import com.kardoaward.kardo.event.model.Event;
import com.kardoaward.kardo.user.mapper.UserMapper;
import com.kardoaward.kardo.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public static CommentDto toCommentDto(Comment comment, Event event, User user) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .event(EventMapper.toEventShortDto(event))
                .author(UserMapper.toUserDto(user))
                .created(comment.getCreated())
                .updated(comment.getUpdated())
                .build();
    }

    public static Comment toComment(CommentDto commentDto, Event event, User user) {
        return Comment.builder()
                .id(commentDto.getId())
                .text(commentDto.getText())
                .event(event)
                .author(user)
                .created(commentDto.getCreated())
                .updated(commentDto.getUpdated())
                .build();
    }

    public static Comment toCommentFromNew(NewCommentDto commentDto, Event event, User user) {
        return Comment.builder()
                .text(commentDto.getText())
                .event(event)
                .author(user)
                .build();
    }
}
