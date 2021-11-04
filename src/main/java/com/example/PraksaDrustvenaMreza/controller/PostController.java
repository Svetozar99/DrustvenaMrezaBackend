package com.example.PraksaDrustvenaMreza.controller;

import java.security.Principal;
import java.util.List;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.PostService;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH})
@RestController
@Api( tags = "Post")
@RequestMapping(value = "api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This method is used to get one post.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getOnePost(@PathVariable("id") Long id){
        try{
            PostDTO p = postService.getOne(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to show home page.")
    @GetMapping(value = "/home")
    public ResponseEntity<List<PostDTO>> getPosts(Principal principal){
        try{
            List<PostDTO> p = postService.getHomePage(principal.getName());
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to add post.")
    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody AddPostDTO postDTO, Principal principal){
        try {
            PostDTO u = postService.save(postDTO,principal.getName());
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "This method is used to update post.")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO, Principal principal){
        PostDTO p = postService.getOne(id);
        UserDTO us = userService.getOneByUserName(principal.getName());
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

    @ApiOperation(value = "This method is used to delete post.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id, Principal principal){
        PostDTO p = postService.getOne(id);
        UserDTO u = userService.getOneByUserName(principal.getName());
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