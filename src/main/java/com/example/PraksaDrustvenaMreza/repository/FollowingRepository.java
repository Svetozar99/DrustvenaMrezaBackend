package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.Following;
import com.example.PraksaDrustvenaMreza.model.RequestForFollowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following, Long> {

    Following findOneById(Long id);

    Following findOneByFollower_userName(String userName);
}
