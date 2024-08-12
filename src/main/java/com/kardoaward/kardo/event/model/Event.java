package com.kardoaward.kardo.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.direction.model.Direction;
import com.kardoaward.kardo.enums.EventType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "events_directions",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id"))
    private Set<Direction> direction = new HashSet<>();

    @Column(name = "date")
    @JsonFormat(pattern = Constants.DATE_FORMAT)
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                type == event.type &&
                Objects.equals(direction, event.direction) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, direction, description);
    }
}
