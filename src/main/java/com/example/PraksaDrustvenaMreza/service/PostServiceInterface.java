package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.*;

public interface PostServiceInterface {

    public PostDTO getOne(Long id);

    public PostDTO save(AddPostDTO DTO);

    public PostDTO update(PostDTO TO, Long id);

    public void delete(Long id);
}
