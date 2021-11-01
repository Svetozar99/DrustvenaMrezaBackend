package com.example.PraksaDrustvenaMreza.controller;

import java.security.Principal;
import java.util.List;

import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import com.example.PraksaDrustvenaMreza.dtos.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.FollowingServiceInterface;

@RestController
@Api( tags = "Following")
@RequestMapping(value = "api/following")
public class FollowingController {

    @Autowired
    private FollowingServiceInterface followingServiceInterface;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This method is used to get followees.")
    @GetMapping(value = "/followees")
    public ResponseEntity<List<FollowingDTO>> getFollowees(Principal principal) {
        try {
            List<FollowingDTO> f = followingServiceInterface.followees(principal.getName());
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to get followers.")
    @GetMapping(value = "/followers")
    public ResponseEntity<List<FollowingDTO>> getFollowers(Principal principal) {
        try {
            List<FollowingDTO> f = followingServiceInterface.followers(principal.getName());
            return new ResponseEntity<>(f, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to delete following.")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFollowing(@PathVariable("id") Long id, Principal principal){
        FollowingDTO f = followingServiceInterface.findOne(id);
        UserDTO u = userService.getOneByUserName(principal.getName());
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
