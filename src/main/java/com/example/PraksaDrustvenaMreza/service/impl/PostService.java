package com.example.PraksaDrustvenaMreza.service.impl;

import com.example.PraksaDrustvenaMreza.dtos.AddPostDTO;
import com.example.PraksaDrustvenaMreza.dtos.PostDTO;
import com.example.PraksaDrustvenaMreza.model.Post;
import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.repository.PostRepository;
import com.example.PraksaDrustvenaMreza.repository.UserRepository;
import com.example.PraksaDrustvenaMreza.service.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
