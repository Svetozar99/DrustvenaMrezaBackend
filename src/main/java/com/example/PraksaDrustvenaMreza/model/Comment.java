package com.example.PraksaDrustvenaMreza.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment extends JpaEntity{

    @Column(name = "body_comment", nullable = false)
    private String bodyComment;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "parent_comment")
    private Long parentComment;
}