package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.FollowingDTO;

public interface FollowingServiceInterface {

    public FollowingDTO findOne(Long id);

    public FollowingDTO save(String userNameFollower);

    public void delete(String userNameFollower);
}
