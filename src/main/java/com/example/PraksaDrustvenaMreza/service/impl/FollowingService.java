package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.example.PraksaDrustvenaMreza.dtos.*;
import com.example.PraksaDrustvenaMreza.model.*;
import com.example.PraksaDrustvenaMreza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.FollowingServiceInterface;

@Service
public class FollowingService implements FollowingServiceInterface {

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public FollowingDTO findOne(Long id) {
        Following following = followingRepository.findOneById(id);

        return new FollowingDTO(following);
    }

    @Override
    public FollowingDTO save(String userNameFollowee) {
        Following following = new Following();

        User followee = userRepository.findOneByUserName(userNameFollowee);
        User follower = userRepository.findOneByUserName("brboric93");

        following.setCreatedAt(LocalDateTime.now());
        following.setFollowee(followee);
        following.setFollower(follower);

        following = followingRepository.save(following);
        requestRepository.deleteById(follower.getId());

        return new FollowingDTO(following);
    }

    @Override
    public void delete(String userNameFollower) {
        Following f = followingRepository.findOneByFollower_userName(userNameFollower);
        followingRepository.deleteById(f.getId());
    }
}
