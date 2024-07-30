package com.kardoaward.kardo.comment.model;

import com.kardoaward.kardo.media.model.Media;
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
@Builder
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
    private Media media;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(media, comment.media) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(created, comment.created) &&
                Objects.equals(updated, comment.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, media, author, created, updated);
    }
}
