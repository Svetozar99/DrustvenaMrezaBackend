package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.*;

import java.util.List;

public interface PostServiceInterface {

    public PostDTO getOne(Long id);

    public PostDTO save(AddPostDTO DTO,String userName);

    public PostDTO update(PostDTO TO, Long id);

    public void delete(Long id);

    public List<PostDTO> getHomePage(String userName);
}
