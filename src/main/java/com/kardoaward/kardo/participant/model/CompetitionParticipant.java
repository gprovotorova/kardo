package com.kardoaward.kardo.participant.model;

import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "competition_participants")
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionParticipant {

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

    @ManyToMany
    @JoinTable(name = "events_directions",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> directions = new HashSet<>();

    public CompetitionParticipant(User user, Competition competition, Set<Direction> directions) {
        this.user = user;
        this.competition = competition;
        this.directions = directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionParticipant that = (CompetitionParticipant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(directions, that.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, competition, directions);
    }
}
