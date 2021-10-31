package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;

import java.util.List;

public interface RequestServiceInterface {

    public List<RequestDTO> getRequests(String userName);

//    public RequestDTO getOne(Long id);

    public RequestDTO save(String userNameReceiver);

    public void delete(Long id);
}