package com.kardoaward.kardo.direction.model;

import com.kardoaward.kardo.enums.DirectionType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private DirectionType name;
}
