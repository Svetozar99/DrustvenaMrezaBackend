package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.PraksaDrustvenaMreza.dtos.*;
import com.example.PraksaDrustvenaMreza.model.*;
import com.example.PraksaDrustvenaMreza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.RequestServiceInterface;

@Service
public class RequestService implements RequestServiceInterface {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RequestDTO findOne(Long id) {
        RequestForFollowing r = requestRepository.findOneById(id);

        return new RequestDTO(r);
    }

    @Override
    public List<RequestDTO> getRequests(String userName) {
        List<RequestForFollowing> requests = requestRepository.findAllByReceiver_userName("brboric93");

        List<RequestDTO> requestDTOS = new ArrayList<>();
        for (RequestForFollowing r: requests){
            requestDTOS.add(new RequestDTO(r));
        }
        return requestDTOS;
    }

    @Override
    public RequestDTO save(String userNameReceiver, String userNameSender) {
        RequestForFollowing requestForFollowing = new RequestForFollowing();

        User sender = userRepository.findOneByUserName(userNameSender);
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
