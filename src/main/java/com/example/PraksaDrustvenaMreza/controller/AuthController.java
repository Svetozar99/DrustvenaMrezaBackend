package com.example.PraksaDrustvenaMreza.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        try {
            UserDTO u = userService.save(userDTO);
            return new ResponseEntity<>(u, HttpStatus.CREATED);
        } catch (IOException ex){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLIntegrityConstraintViolationException ex){
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
    }
}
