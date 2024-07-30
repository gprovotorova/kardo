package com.kardoaward.kardo.media.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "media")
@Builder(toBuilder = true)
@Data
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
