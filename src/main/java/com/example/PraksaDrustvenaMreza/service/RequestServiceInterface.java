package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.RequestDTO;

public interface RequestServiceInterface {

    public RequestDTO getOne(Long id);

    public RequestDTO save(String userNameReceiver);

    public void delete(Long id);
}