package com.kardoaward.kardo.participant.model;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directionn_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Direction direction;

    @Column(name = "id")
    private String userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(id, that.id)
                && Objects.equals(user, that.user)
                && Objects.equals(competition, that.competition)
                && Objects.equals(direction, that.direction)
                && Objects.equals(userStatus, that.userStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, competition, direction, userStatus);
    }
}
