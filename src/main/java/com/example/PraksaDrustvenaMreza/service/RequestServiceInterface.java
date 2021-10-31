package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;

import java.util.List;

public interface RequestServiceInterface {

    public List<RequestDTO> getRequests(String userName);

    public RequestDTO save(String userNameReceiver, String userNameSender);

    public void delete(Long id);
}