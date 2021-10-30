package com.example.PraksaDrustvenaMreza.service.impl;

import com.example.PraksaDrustvenaMreza.dtos.UserDTO;
import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.repository.UserRepository;
import com.example.PraksaDrustvenaMreza.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public UserDTO save(UserDTO u) {

        User user = new User();
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
