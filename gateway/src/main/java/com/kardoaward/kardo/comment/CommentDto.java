package com.kardoaward.kardo.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {

    private Long id;
    @NotBlank
    private String text;
    private String authorName;
    private LocalDateTime created;
}
