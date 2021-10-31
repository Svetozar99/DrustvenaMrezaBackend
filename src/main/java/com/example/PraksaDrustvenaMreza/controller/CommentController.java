package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.AddCommentDTO;
import com.example.PraksaDrustvenaMreza.dtos.CommentDTO;
import com.example.PraksaDrustvenaMreza.service.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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