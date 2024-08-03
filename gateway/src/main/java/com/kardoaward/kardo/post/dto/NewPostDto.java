package com.kardoaward.kardo.post.dto;

import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;
import org.apache.tomcat.jni.FileInfo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostDto {

    private FileInfo file;

    private String description;

    private Long likes;

    private Long dislikes;

    private UserDto author;

    @Enumerated(EnumType.STRING)
    private DirectionType direction;

    private LocalDateTime createdDate;
}
