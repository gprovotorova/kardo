package com.kardoaward.kardo.direction;

import com.kardoaward.kardo.enums.DirectionType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
public class DirectionDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private DirectionType direction;
}
