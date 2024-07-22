package com.kardoaward.kardo.comment.service;

import com.kardoaward.kardo.comment.dto.CommentDto;
import com.kardoaward.kardo.comment.dto.NewCommentDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {
    CommentDto createComment(Long userId, Long eventId, NewCommentDto commentDto);

    CommentDto updateComment(Long userId, Long eventId, Long commentId, NewCommentDto commentDto);

    void deleteCommentByUser(Long userId, Long commentId);

    List<CommentDto> getAllByEventIdAndUserId(Long eventId, Long userId, Pageable page);

    List<CommentDto> getAllByUserId(Long userId, Pageable page);

    CommentDto getByCommentId(Long commentId, Long userId);

    List<CommentDto> getByEventId(Long eventId, Pageable page);

    List<CommentDto> getAllComments(Pageable page);

    List<CommentDto> getCommentsWithFilters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable page);
}
