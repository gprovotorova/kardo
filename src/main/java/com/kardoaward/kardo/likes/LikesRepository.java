package com.kardoaward.kardo.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("select l from Likes l " +
            "where l.type = :type " +
            "and l.post = :postId")
    Long getLikesOrDislikes(Long postId, String type);
}
