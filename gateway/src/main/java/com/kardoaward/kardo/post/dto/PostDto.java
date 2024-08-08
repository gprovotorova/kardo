package com.kardoaward.kardo.post.dto;

import com.kardoaward.kardo.comment.CommentDto;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;
import org.apache.tomcat.jni.FileInfo;

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

    private CommentDto comment;

    private UserDto author;

    private DirectionType direction;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}