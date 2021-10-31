package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public List<FollowingDTO> followees(String userName) {
        List<Following> followings = followingRepository.findByFollowee_userName(userName);

        List<FollowingDTO> followingDTOS = new ArrayList<>();
        for(Following f: followings){
            followingDTOS.add(new FollowingDTO(f));
        }
        return followingDTOS;
    }

    @Override
    public List<FollowingDTO> followers(String userName) {
        List<Following> followings = followingRepository.findOneByFollower_userName(userName);

        List<FollowingDTO> followingDTOS = new ArrayList<>();
        for(Following f: followings){
            followingDTOS.add(new FollowingDTO(f));
        }
        return followingDTOS;
    }

    @Override
    public FollowingDTO findOne(Long id) {
        Following following = followingRepository.findOneById(id);

        return new FollowingDTO(following);
    }

    @Override
    public FollowingDTO save(String userNameFollower, String userNameFollowee) {

        RequestForFollowing request = requestRepository.findOneByReceiver_userNameAndSender_userName(userNameFollower, userNameFollowee);


        Following following = new Following();

        User followee = userRepository.findOneByUserName(userNameFollowee);
        User follower = userRepository.findOneByUserName(userNameFollower);

        following.setCreatedAt(LocalDateTime.now());
        following.setFollowee(followee);
        following.setFollower(follower);

        following = followingRepository.save(following);
        requestRepository.deleteById(request.getId());

        return new FollowingDTO(following);
    }

    @Override
    public void delete(Long id) {
        followingRepository.deleteById(id);
    }
}
