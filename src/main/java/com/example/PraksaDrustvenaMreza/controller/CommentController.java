package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.service.impl.UserService;
import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.CommentServiceInterface;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/comment")
public class CommentController {

    @Autowired
    private CommentServiceInterface commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentDTO addCommentDTO, Principal principal) {
        try {
            CommentDTO c = commentService.save(addCommentDTO, principal.getName());
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO, Principal principal){
        CommentDTO co = commentService.getOne(id);
        UserDTO u = userService.getOneByUserName(principal.getName());
        try{
            if(u.getUserName().equals(co.getUserDTO().getUserName())) {
                CommentDTO c = commentService.update(commentDTO, id);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id, Principal principal){
        CommentDTO c = commentService.getOne(id);
        UserDTO u = userService.getOneByUserName(principal.getName());
        try{
            if(u.getUserName().equals(c.getUserDTO().getUserName())) {
                commentService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}