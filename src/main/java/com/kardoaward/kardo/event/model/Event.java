package com.kardoaward.kardo.event.model;

import com.kardoaward.kardo.Direction;
import com.kardoaward.kardo.enums.EventType;
import com.kardoaward.kardo.location.Location;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToOne(fetch = FetchType.LAZY) //TODO или через ENUM
    @JoinColumn(name = "direction_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Direction direction;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ToString.Exclude
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && type == event.type && Objects.equals(direction, event.direction) && Objects.equals(description, event.description) && Objects.equals(location, event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, direction, description, location);
    }
}
