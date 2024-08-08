package com.kardoaward.kardo.post.dto;

import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostDto {

    private FileInfo file;

    private String description;

    private User author;

    @Enumerated(EnumType.STRING)
    private DirectionType direction;

    private LocalDateTime createdDate;
}
