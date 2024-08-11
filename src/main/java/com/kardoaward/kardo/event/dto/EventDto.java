package com.kardoaward.kardo.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private Set<Direction> direction;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate date;

    private String description;
}
