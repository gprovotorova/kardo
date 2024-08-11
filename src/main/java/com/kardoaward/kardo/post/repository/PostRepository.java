package com.kardoaward.kardo.post.repository;

import com.kardoaward.kardo.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByAuthor_Id(Long authorId, Pageable page);

}
