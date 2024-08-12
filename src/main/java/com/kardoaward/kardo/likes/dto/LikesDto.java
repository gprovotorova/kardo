package com.kardoaward.kardo.likes.dto;

import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikesDto {

    @NotNull
    private Post post;

    @NotNull
    private User user;

    @NotNull
    private String type;
}
