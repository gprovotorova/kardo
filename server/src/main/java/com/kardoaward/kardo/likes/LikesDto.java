package com.kardoaward.kardo.likes;

import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
