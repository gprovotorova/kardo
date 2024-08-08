package com.kardoaward.kardo.stage.dto;

import com.kardoaward.kardo.enums.StageStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private StageStatus status;
}

