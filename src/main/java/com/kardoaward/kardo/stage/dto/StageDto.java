package com.kardoaward.kardo.stage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.enums.StageStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StageDto {

    private Long id;
    private String name;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private StageStatus status;
}

