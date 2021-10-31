package com.example.PraksaDrustvenaMreza.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        try {
            UserDTO u = userService.save(userDTO);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
