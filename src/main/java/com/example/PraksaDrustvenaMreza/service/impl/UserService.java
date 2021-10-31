package com.example.PraksaDrustvenaMreza.service.impl;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.example.PraksaDrustvenaMreza.dtos.*;
import com.example.PraksaDrustvenaMreza.model.*;
import com.example.PraksaDrustvenaMreza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getOne(Long id) {
        User u = userRepository.findOneById(id);

        return new UserDTO(u);
    }

    @Override
    public UserDTO getOneByUserName(String userName) {
        User u = userRepository.findOneByUserName(userName);

        return new UserDTO(u);
    }

    @Override
    public UserDTO save(UserDTO u) throws IOException, SQLIntegrityConstraintViolationException{
        boolean isUnique = false;
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setUserName(u.getUserName());
        if(u.getPassword().equals(u.getRepeatedPassword())){
            user.setPassword(u.getPassword());
            user.setRepeatedPassword(u.getRepeatedPassword());
        }else{
            throw new IOException("Password not valid");
        }
        user = userRepository.save(user);

        return new UserDTO(user);
    }

    @Override
    public UserDTO update(UserDTO u, Long id) {
        User user = userRepository.findOneById(id);
        user.setCreatedAt(LocalDateTime.now());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setUserName(u.getUserName());
        user.setPassword(u.getPassword());
        user.setRepeatedPassword(u.getRepeatedPassword());

        user = userRepository.save(user);

        return new UserDTO(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
