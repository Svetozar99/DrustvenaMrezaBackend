package com.example.PraksaDrustvenaMreza.controller;

import java.util.List;

import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping(value = "/followees")
    public ResponseEntity<List<FollowingDTO>> getFollowees() {
        try {
            List<FollowingDTO> f = followingServiceInterface.followees("brboric99");
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/followers")
    public ResponseEntity<List<FollowingDTO>> getFollowers() {
        try {
            List<FollowingDTO> f = followingServiceInterface.followers("brboric99");
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteFollowing(@PathVariable("id") Long id){
        FollowingDTO f = followingServiceInterface.findOne(id);
        UserDTO u = userService.getOneByUserName("brboric99");
        try{
            if(u.getUserName().equals(f.getSender().getUserName())) {
                followingServiceInterface.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
