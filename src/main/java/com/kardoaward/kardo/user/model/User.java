package com.kardoaward.kardo.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.country.model.Country;
import com.kardoaward.kardo.enums.UserType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.region.model.Region;
import com.kardoaward.kardo.enums.UserGender;
import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_surname", nullable = false)
    private String surname;

    @Column(name = "user_patronymic")
    private String patronymic;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_birthday", nullable = false)
    @JsonFormat(pattern = Constants.DATE_FORMAT)
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

    @ElementCollection
    @CollectionTable(name = "user_links", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "link")
    private List<String> links;

    @Column(name = "user_phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "user_nationality", nullable = false)
    private String nationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    @ToString.Exclude
    private FileInfo file;

    @Column(name = "portfolio")
    private URL portfolio;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(patronymic, user.patronymic) && type == user.type && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(birthday, user.birthday) && Objects.equals(country, user.country) && Objects.equals(region, user.region) && Objects.equals(city, user.city) && Objects.equals(links, user.links) && Objects.equals(phone, user.phone) && gender == user.gender && Objects.equals(nationality, user.nationality) && Objects.equals(file, user.file) && Objects.equals(portfolio, user.portfolio) && Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, type, email, password, birthday, country, region, city, links, phone, gender, nationality, file, portfolio, description);
    }
}
