package com.kardoaward.kardo.event.dto;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.location.Location;
import lombok.Data;
@Data
public class EventDto {
    private String name;
    private EventType type;
    private Direction direction;
    private String description;
    private Location location;
}
