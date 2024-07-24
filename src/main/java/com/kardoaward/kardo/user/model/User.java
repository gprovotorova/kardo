package com.kardoaward.kardo.user.model;

import com.kardoaward.kardo.country.model.Country;
import com.kardoaward.kardo.photo.Photo;
import com.kardoaward.kardo.region.Region;
import com.kardoaward.kardo.enums.UserGender;
import com.kardoaward.kardo.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Region region;

    @Column(name = "city", nullable = false)
    private String city;

    @ToString.Exclude
    @ManyToMany(mappedBy = "links")
    private Set<String> links = new HashSet<>();

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    @ToString.Exclude
    private Photo photo;

    @Column(name = "portfolio") //TODO ссылка
    private String portfolio;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(surname, user.surname)
                && Objects.equals(name, user.name)
                && Objects.equals(patronymic, user.patronymic)
                && type == user.type
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(birthday, user.birthday)
                && Objects.equals(country, user.country)
                && Objects.equals(region, user.region)
                && Objects.equals(city, user.city)
                && Objects.equals(links, user.links)
                && Objects.equals(phone, user.phone)
                && gender == user.gender
                && Objects.equals(nationality, user.nationality)
                && Objects.equals(photo, user.photo)
                && Objects.equals(portfolio, user.portfolio)
                && Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, type, email, password, birthday, country, region, city,
                links, phone, gender, nationality, photo, portfolio, description);
    }
}
