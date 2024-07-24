package com.kardoaward.kardo.stage.mapper;

import com.kardoaward.kardo.stage.dto.StageDto;
import com.kardoaward.kardo.stage.model.StageOfCompetition;
import org.springframework.stereotype.Component;

@Component
public class StageMapper {
    public static StageDto toStageDto(StageOfCompetition stage) {
        return new StageDto(
                stage.getId(),
                stage.getName(),
                stage.getStartDate(),
                stage.getEndDate(),
                stage.getStatus()
        );
    }

    public static StageOfCompetition toStage(StageDto stageDto) {
        return new StageOfCompetition(
                stageDto.getId(),
                stageDto.getName(),
                stageDto.getStartDate(),
                stageDto.getEndDate(),
                stageDto.getStatus()
        );
    }
}
