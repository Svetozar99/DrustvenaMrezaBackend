package com.example.PraksaDrustvenaMreza.controller;

import com.example.PraksaDrustvenaMreza.dtos.JwtDTO;
import com.example.PraksaDrustvenaMreza.dtos.LoginDTO;
import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.security.TokenUtils;
import com.example.PraksaDrustvenaMreza.service.impl.UserDetailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.impl.UserService;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@Api( tags = "Auth")
@RequestMapping(value = "api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @SuppressWarnings("unused")
    @ApiOperation(value = "This method is used to login.")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtDTO> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("\nLogin-------<<<<");
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUserName(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUserName());
            UserDTO u = userService.getOneByUserName(details.getUsername());
            JwtDTO t = new JwtDTO(tokenUtils.generateToken(details), u.getId());
            return new ResponseEntity<JwtDTO>(t, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(401).build();
        }
    }

    @ApiOperation(value = "This method is used to register.")
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
