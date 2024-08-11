package com.kardoaward.kardo.partners.model;

import com.kardoaward.kardo.enums.PartnerType;
import com.kardoaward.kardo.file.model.FileInfo;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partners")
@Builder
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private FileInfo icon;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

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
                && Objects.equals(icon, partner.icon)
                && Objects.equals(name, partner.name)
                && Objects.equals(type, partner.type)
                && Objects.equals(description, partner.description)
                && Objects.equals(link, partner.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, icon, name, type, description, link);
    }
}

