package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findOneById(Long id);

    @Query("SELECT p FROM Post p " +
            "WHERE p.user.userName = :userName " +
            "or p.user.id in (SELECT f.follower.id FROM Following f " +
            "WHERE f.followee.userName = :userName)")
    List<Post> findHomePage(String userName);
}
