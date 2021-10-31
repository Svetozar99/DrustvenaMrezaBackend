package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public PostDTO save(AddPostDTO DTO, String userName) {
        Post p = new Post();
        User u = userRepository.findOneByUserName(userName);
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

    @Override
    public List<PostDTO> getHomePage(String userName) {
        List<Post> posts = postRepository.findHomePage(userName);

        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post p: posts){
            postDTOS.add(new PostDTO(p));
        }
        return postDTOS;
    }
}
