package com.example.PraksaDrustvenaMreza.model;

import lombok.*;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post extends JpaEntity{

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> comments;
}
