package com.kardoaward.kardo.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.user.dto.UserDto;
import lombok.*;

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

    private UserDto author;

    @Enumerated(EnumType.STRING)
    private DirectionType direction;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdDate;
}
