package com.kardoaward.kardo.participant.model;

import com.kardoaward.kardo.competition.model.Competition;
import com.kardoaward.kardo.stage.model.StageOfCompetition;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "evaluating")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluatingParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private StageOfCompetition stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private CompetitionParticipant participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private User expert;

    @Column(name = "points", columnDefinition = "0")
    private Double points;

    public EvaluatingParticipant(Competition competition,
                                 StageOfCompetition stage,
                                 CompetitionParticipant participant,
                                 User expert,
                                 Double points) {
        this.competition = competition;
        this.stage = stage;
        this.participant = participant;
        this.expert = expert;
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluatingParticipant that = (EvaluatingParticipant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(stage, that.stage) &&
                Objects.equals(participant, that.participant) &&
                Objects.equals(expert, that.expert) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, competition, stage, participant, expert, points);
    }
}
