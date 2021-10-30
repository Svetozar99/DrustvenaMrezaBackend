package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.AddPostDTO;
import com.example.PraksaDrustvenaMreza.dtos.PostDTO;
import com.example.PraksaDrustvenaMreza.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("id") Long id){
        try{
            PostDTO p = postService.getOne(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody AddPostDTO postDTO){
        try {
            PostDTO u = postService.save(postDTO);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO){
        try{
            PostDTO u = postService.update(postDTO, id);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        try{
            postService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
