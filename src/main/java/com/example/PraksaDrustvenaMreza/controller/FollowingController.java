package com.example.PraksaDrustvenaMreza.controller;

import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.FollowingServiceInterface;

@RestController
@RequestMapping(value = "api/following")
public class FollowingController {

    @Autowired
    private FollowingServiceInterface followingServiceInterface;

    @GetMapping(value = "{id}")
    public ResponseEntity<FollowingDTO> getOneFollowing(@PathVariable("id") Long id) {
        try {
            FollowingDTO f = followingServiceInterface.findOne(id);
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{follower}")
    public ResponseEntity<Void> deleteFollowing(@PathVariable("follower") String follower){
        try{
            followingServiceInterface.delete(follower);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
