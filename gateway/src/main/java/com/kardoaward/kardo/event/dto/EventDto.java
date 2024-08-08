package com.kardoaward.kardo.event.dto;

import com.kardoaward.kardo.direction.DirectionDto;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.location.LocationDto;
import lombok.Data;

import java.util.Set;

@Data
public class EventDto {
    private String name;
    private EventType type;
    private Set<DirectionDto> directions;
    private String description;
    private LocationDto location;
}
