package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.RequestForFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestForFollowing, Long> {

    RequestForFollowing findOneById(Long id);
}
