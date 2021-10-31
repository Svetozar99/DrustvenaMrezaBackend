package com.example.PraksaDrustvenaMreza.controller;

import java.util.List;
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

    @GetMapping(value = "followees/{userName}")
    public ResponseEntity<List<FollowingDTO>> getFollowees(@PathVariable("userName") String userName) {
        try {
            List<FollowingDTO> f = followingServiceInterface.followees(userName);
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "followers/{userName}")
    public ResponseEntity<List<FollowingDTO>> getFollowers(@PathVariable("userName") String userName) {
        try {
            List<FollowingDTO> f = followingServiceInterface.followers(userName);
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteFollowing(@PathVariable("id") Long id){
        try{
            followingServiceInterface.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
