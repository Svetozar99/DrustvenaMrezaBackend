package com.example.PraksaDrustvenaMreza.controller;

import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.CommentServiceInterface;

@RestController
@RequestMapping(value = "api/comment")
public class CommentController {

    @Autowired
    private CommentServiceInterface commentService;

    @GetMapping(value = "{id}")
    public ResponseEntity<CommentDTO> getOneComment(@PathVariable("id") Long id) {
        try {
            CommentDTO c = commentService.getOne(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentDTO addCommentDTO) {
        try {
            CommentDTO c = commentService.save(addCommentDTO);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "{id}")
    public ResponseEntity<CommentDTO> updateComments(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO){
        try{
            CommentDTO c = commentService.update(commentDTO, id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id){
        try{
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}