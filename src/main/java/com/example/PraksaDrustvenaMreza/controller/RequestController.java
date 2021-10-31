package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.AddCommentDTO;
import com.example.PraksaDrustvenaMreza.dtos.CommentDTO;
import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;
import com.example.PraksaDrustvenaMreza.service.RequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/request")
public class RequestController {

    @Autowired
    private RequestServiceInterface requestService;

    @GetMapping(value = "{id}")
    public ResponseEntity<RequestDTO> getOneComment(@PathVariable("id") Long id) {
        try {
            RequestDTO r = requestService.getOne(id);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{receiver}")
    public ResponseEntity<RequestDTO> sendRequest(@PathVariable("receiver") String receiver) {
        try {
            RequestDTO r = requestService.save(receiver);
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id){
        try{
            requestService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
