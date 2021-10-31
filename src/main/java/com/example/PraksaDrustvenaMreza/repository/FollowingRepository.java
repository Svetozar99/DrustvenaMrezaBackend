package com.example.PraksaDrustvenaMreza.repository;

import java.util.List;
import com.example.PraksaDrustvenaMreza.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following, Long> {

    Following findOneById(Long id);

    List<Following> findOneByFollower_userName(String userName);

    List<Following> findByFollowee_userName(String userName);
}
