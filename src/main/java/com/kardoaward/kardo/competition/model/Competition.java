package com.kardoaward.kardo.competition.model;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.stage.model.StageOfCompetition;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Direction direction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private StageOfCompetition stage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(direction, that.direction)
                && Objects.equals(stage, that.stage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, direction, stage);
    }
}
