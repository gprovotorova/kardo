package com.kardoaward.kardo.comment.repository;

import com.kardoaward.kardo.comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByAuthorId(Long userId, Pageable page);

    Page<Comment> findAllByPostId(Long postId, Pageable page);
}
