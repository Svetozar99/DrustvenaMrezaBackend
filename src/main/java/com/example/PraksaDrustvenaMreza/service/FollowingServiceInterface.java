package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.FollowingDTO;

import java.util.List;

public interface FollowingServiceInterface {

    public List<FollowingDTO> followees(String userName);

    public List<FollowingDTO> followers(String userName);

    public FollowingDTO findOne(Long id);

    public FollowingDTO save(String userNameFollower,String userNameFollowee);

    public void delete(Long id);
}
