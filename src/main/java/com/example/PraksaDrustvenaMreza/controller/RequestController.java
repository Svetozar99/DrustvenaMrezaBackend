package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.FollowingDTO;
import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import com.example.PraksaDrustvenaMreza.service.FollowingServiceInterface;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.RequestServiceInterface;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/request")
public class RequestController {

    @Autowired
    private RequestServiceInterface requestService;

    @Autowired
    private FollowingServiceInterface followingServiceInterface;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getRequests(Principal principal) {
        try {
            List<RequestDTO> r = requestService.getRequests(principal.getName());
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{receiver}")
    public ResponseEntity<RequestDTO> sendRequest(@PathVariable("receiver") String receiver, Principal principal) {
        try {
            RequestDTO r = requestService.save(receiver, principal.getName());
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "accept/{sender}")
    public ResponseEntity<FollowingDTO> acceptRequest(@PathVariable("sender") String sender,Principal principal) {
        try {
            FollowingDTO r = followingServiceInterface.save(principal.getName(), sender);
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/reject/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id, Principal principal){
        RequestDTO r = requestService.findOne(id);
        UserDTO u = userService.getOneByUserName(principal.getName());
        try{
            if(u.getUserName().equals(r.getSender().getUserName())) {
                requestService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
