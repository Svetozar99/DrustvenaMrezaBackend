package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.model.Comment;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.CommentServiceInterface;

import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH})
@RestController
@Api( tags = "Comment")
@RequestMapping(value = "api/comment")
public class CommentController {

    @Autowired
    private CommentServiceInterface commentService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This method is used to add comment.")
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody AddCommentDTO addCommentDTO, Principal principal) {
        try {
            CommentDTO c = commentService.save(addCommentDTO, principal.getName());
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "This method is used to update comment.")
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

    @ApiOperation(value = "This method is used to delete comment.")
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

    @GetMapping(value = "/{postId}")
    public ResponseEntity<List<CommentDTO>> getByPost(@PathVariable("postId") Long id){
        try{
            List<CommentDTO> c = commentService.findByPost(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}