package com.kardoaward.kardo.partners.model;

import com.kardoaward.kardo.photo.entity.Photo;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Photo photo;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partner partner = (Partner) o;
        return Objects.equals(id, partner.id)
                && Objects.equals(photo, partner.photo)
                && Objects.equals(name, partner.name)
                && Objects.equals(type, partner.type)
                && Objects.equals(description, partner.description)
                && Objects.equals(link, partner.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo, name, type, description, link);
    }
}

