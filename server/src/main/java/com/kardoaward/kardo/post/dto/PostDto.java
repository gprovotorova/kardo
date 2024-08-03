package com.kardoaward.kardo.post.dto;

import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long id;

    private FileInfo file;

    private String description;

    private Long likes;

    private Long dislikes;

    private Comment comment;

    private User author;

    private DirectionType direction;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}