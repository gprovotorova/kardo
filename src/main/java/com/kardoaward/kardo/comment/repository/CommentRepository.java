package com.kardoaward.kardo.comment.repository;

import com.kardoaward.kardo.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c " +
            "where c.created between :rangeStart and :rangeEnd " +
            "and c.updated between :rangeStart and :rangeEnd")
    Page<Comment> getCommentsWithFilters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable page);

    Page<Comment> findAllByEventIdAndAuthorId(Long eventId, Long userId, Pageable page);

    Page<Comment> findAllByAuthorId(Long userId, Pageable page);

    Page<Comment> findAllByEventId(Long eventId, Pageable page);
}
