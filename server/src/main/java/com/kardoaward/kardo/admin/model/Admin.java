package com.kardoaward.kardo.admin.model;

import com.kardoaward.kardo.file.model.FileInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "admin_links", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "link")
    private List<String> links;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    @ToString.Exclude
    private FileInfo fileInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) && Objects.equals(name, admin.name) && Objects.equals(surname, admin.surname) && Objects.equals(email, admin.email) && Objects.equals(password, admin.password) && Objects.equals(links, admin.links) && Objects.equals(phone, admin.phone) && Objects.equals(fileInfo, admin.fileInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, links, phone, fileInfo);
    }
}
