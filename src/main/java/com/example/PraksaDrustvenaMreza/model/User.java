package com.example.PraksaDrustvenaMreza.model;

import lombok.*;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends JpaEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "repeated_password", nullable = false)
    private String repeatedPassword;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "followee")
    private List<Following> folloewees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "follower")
    private List<Following> followers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")
    private List<RequestForFollowing> senders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receiver")
    private List<RequestForFollowing> receivers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;
}
