package com.kardoaward.kardo.likes.mapper;

import com.kardoaward.kardo.likes.model.Likes;
import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;

public class LikesMapper {
    public static Likes toLikesOrDislikes(User user, Post post, String type) {
        return Likes.builder()
                .user(user)
                .post(post)
                .type(type)
                .build();
    }
}
