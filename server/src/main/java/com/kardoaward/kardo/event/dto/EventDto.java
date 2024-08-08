package com.kardoaward.kardo.event.dto;

import com.kardoaward.kardo.direction.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.location.Location;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
public class EventDto {

    private String name;

    private EventType type;

    @Enumerated(EnumType.STRING)
    private Set<Direction> direction;

    private String description;

    private Location location;
}
