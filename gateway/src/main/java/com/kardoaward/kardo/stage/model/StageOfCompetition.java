package com.kardoaward.kardo.stage.model;

import com.kardoaward.kardo.enums.StageStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stages")
@Builder
public class StageOfCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @CreationTimestamp
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private StageStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageOfCompetition that = (StageOfCompetition) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, status);
    }
}

