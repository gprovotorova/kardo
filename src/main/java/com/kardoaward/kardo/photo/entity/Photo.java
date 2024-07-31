package com.kardoaward.kardo.photo.entity;

import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "photo")
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "link")
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @Column(name = "created")
    private LocalDateTime created;
}
