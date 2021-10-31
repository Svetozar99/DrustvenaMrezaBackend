package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findOneById(Long id);
}
