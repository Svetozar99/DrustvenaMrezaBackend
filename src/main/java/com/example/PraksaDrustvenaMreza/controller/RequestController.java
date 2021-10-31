package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.FollowingDTO;
import com.example.PraksaDrustvenaMreza.service.FollowingServiceInterface;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.RequestServiceInterface;

import java.util.List;

@RestController
@RequestMapping(value = "api/request")
public class RequestController {

    @Autowired
    private RequestServiceInterface requestService;

    @Autowired
    private FollowingServiceInterface followingServiceInterface;

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getRequests() {
        try {
            List<RequestDTO> r = requestService.getRequests("brboric93");
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{receiver}")
    public ResponseEntity<RequestDTO> sendRequest(@PathVariable("receiver") String receiver) {
        try {
            RequestDTO r = requestService.save(receiver, "brboric99");
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "accept/{sender}")
    public ResponseEntity<FollowingDTO> acceptRequest(@PathVariable("sender") String sender) {
        try {
            FollowingDTO r = followingServiceInterface.save("brboric93", sender);
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/reject/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id){
        try{
            requestService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
