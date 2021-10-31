package com.example.PraksaDrustvenaMreza.controller;

import java.util.List;


import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.PostService;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;

@RestController
@RequestMapping(value = "api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("id") Long id){
        try{
            PostDTO p = postService.getOne(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "home")
    public ResponseEntity<List<PostDTO>> getPosts(){
        try{
            List<PostDTO> p = postService.getHomePage("brboric99");
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody AddPostDTO postDTO){
        try {
            PostDTO u = postService.save(postDTO,"brboric93");
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO){
        PostDTO p = postService.getOne(id);
        UserDTO us = userService.getOneByUserName("brboric99");
        try{
            if(us.getUserName().equals(p.getUserDTO().getUserName())){
                PostDTO u = postService.update(postDTO, id);
                return new ResponseEntity<>(u, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        PostDTO p = postService.getOne(id);
        UserDTO u = userService.getOneByUserName("brboric99");
        try{
            if(u.getUserName().equals(p.getUserDTO().getUserName())){
                postService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}