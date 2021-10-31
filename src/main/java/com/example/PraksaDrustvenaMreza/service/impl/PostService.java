package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.example.PraksaDrustvenaMreza.dtos.*;
import com.example.PraksaDrustvenaMreza.model.*;
import com.example.PraksaDrustvenaMreza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.PostServiceInterface;

@Service
public class PostService implements PostServiceInterface {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDTO getOne(Long id) {
        Post p = postRepository.findOneById(id);

        return new PostDTO(p);
    }

    @Override
    public PostDTO save(AddPostDTO DTO) {
        Post p = new Post();
        User u = userRepository.findOneByUserName("brboric99");//dok ne uradim security
        p.setBody(DTO.getBody());
        p.setUser(u);
        p.setCreatedAt(LocalDateTime.now());
        p = postRepository.save(p);

        return new PostDTO(p);
    }

    @Override
    public PostDTO update(PostDTO DTO, Long id) {
        Post p = postRepository.findOneById(id);
        p.setBody(DTO.getBody());
        p.setCreatedAt(LocalDateTime.now());
        p = postRepository.save(p);

        return new PostDTO(p);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
