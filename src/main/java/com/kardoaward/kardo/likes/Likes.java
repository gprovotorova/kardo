package com.kardoaward.kardo.likes;

import com.kardoaward.kardo.post.model.Post;
import com.kardoaward.kardo.user.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_likes")
@Builder
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @Column(name = "type", nullable = false)
    private String type;
}