package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findOneById(Long id);

    List<Comment> findByPost_id(Long id);
}
