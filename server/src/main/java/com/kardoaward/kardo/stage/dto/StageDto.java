package com.kardoaward.kardo.stage.dto;

import com.kardoaward.kardo.enums.DirectionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StageDto {

    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private DirectionType status;
}

