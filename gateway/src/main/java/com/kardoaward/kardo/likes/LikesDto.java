package com.kardoaward.kardo.likes;

import com.kardoaward.kardo.post.dto.PostDto;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikesDto {

    @NotNull
    private PostDto post;

    @NotNull
    private UserDto user;

    @NotNull
    private String type;
}
