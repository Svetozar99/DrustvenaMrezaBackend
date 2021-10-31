package com.example.PraksaDrustvenaMreza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PraksaDrustvenaMreza.model.RequestForFollowing;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestForFollowing, Long> {

    RequestForFollowing findOneById(Long id);

    List<RequestForFollowing> findAllByReceiver_userName(String userName);
}
