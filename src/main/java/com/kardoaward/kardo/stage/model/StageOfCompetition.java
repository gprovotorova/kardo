package com.kardoaward.kardo.stage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.enums.StageStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stage")
@Builder
public class StageOfCompetition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    @Column(name = "start_date")
    private LocalDate startDate;

    @JsonFormat(pattern = Constants.DATE_FORMAT)
    @Column(name = "end_date")
    private LocalDate endDate;

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

