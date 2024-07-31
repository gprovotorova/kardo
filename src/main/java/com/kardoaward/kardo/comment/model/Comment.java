package com.kardoaward.kardo.comment.model;

import com.kardoaward.kardo.photo.entity.Photo;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    //TODO добавить тип
   // elementType

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    public Comment(String text, Photo photo, User author, LocalDateTime created) {
        this.text = text;
        this.photo = photo;
        this.author = author;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(photo, comment.photo) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(created, comment.created) &&
                Objects.equals(updated, comment.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, photo, author, created, updated);
    }
}
