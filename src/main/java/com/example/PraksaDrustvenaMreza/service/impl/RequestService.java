package com.example.PraksaDrustvenaMreza.service.impl;

import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;
import com.example.PraksaDrustvenaMreza.model.RequestForFollowing;
import com.example.PraksaDrustvenaMreza.model.User;
import com.example.PraksaDrustvenaMreza.repository.RequestRepository;
import com.example.PraksaDrustvenaMreza.repository.UserRepository;
import com.example.PraksaDrustvenaMreza.service.RequestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestService implements RequestServiceInterface {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RequestDTO getOne(Long id) {
        RequestForFollowing requestForFollowing = requestRepository.findOneById(id);

        return new RequestDTO(requestForFollowing);
    }

    @Override
    public RequestDTO save(String userNameReceiver) {
        RequestForFollowing requestForFollowing = new RequestForFollowing();

        User sender = userRepository.findOneByUserName("brboric99");
        User receiver = userRepository.findOneByUserName(userNameReceiver);

        requestForFollowing.setCreatedAt(LocalDateTime.now());
        requestForFollowing.setSender(sender);
        requestForFollowing.setReceiver(receiver);

        requestForFollowing = requestRepository.save(requestForFollowing);

        return new RequestDTO(requestForFollowing);
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
