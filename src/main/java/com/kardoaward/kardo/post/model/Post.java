package com.kardoaward.kardo.post.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kardoaward.kardo.comment.model.Comment;
import com.kardoaward.kardo.common.Constants;
import com.kardoaward.kardo.enums.DirectionType;
import com.kardoaward.kardo.file.model.FileInfo;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private FileInfo file;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private User author;

    @Enumerated(EnumType.STRING)
    private DirectionType direction;

    @Column(name = "created_date")
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(file, post.file) && Objects.equals(description, post.description) && Objects.equals(likes, post.likes) && Objects.equals(dislikes, post.dislikes) && Objects.equals(author, post.author) && direction == post.direction && Objects.equals(createdDate, post.createdDate) && Objects.equals(updatedDate, post.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, file, description, likes, dislikes, author, direction, createdDate, updatedDate);
    }
}
