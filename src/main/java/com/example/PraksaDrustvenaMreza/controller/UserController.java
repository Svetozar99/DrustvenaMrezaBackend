package com.example.PraksaDrustvenaMreza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PATCH})
@RestController
@Api( tags = "User")
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "This method is used to get one user.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable("id") Long id){
        try{
            UserDTO u = userService.getOne(id);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to show profile page.")
    @GetMapping(value = "/search/{value}")
    public ResponseEntity<List<UserDTO>> search(@PathVariable("value") String value){
        List<UserDTO> u = userService.search(value);
        try{
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to show profile page.")
    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> profile(Principal principal){
        UserDTO userDTO = userService.getOneByUserName(principal.getName());
        try{
            UserDTO u = userService.getOne(userDTO.getId());
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to update profile.")
    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Principal principal){
        UserDTO us = userService.getOneByUserName(principal.getName());
        try{
            UserDTO u = userService.update(userDTO, us.getId());
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "This method is used to delete profile.")
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Principal principal){
        UserDTO u = userService.getOneByUserName(principal.getName());
        try{
            userService.delete(u.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
