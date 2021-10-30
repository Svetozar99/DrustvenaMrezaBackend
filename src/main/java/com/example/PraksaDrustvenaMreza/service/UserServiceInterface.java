package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.UserDTO;

public interface UserServiceInterface {

    public UserDTO getOne(Long id);

    public UserDTO save(UserDTO userDTO);

    public UserDTO update(UserDTO userDTO, Long id);

    public void delete(Long id);
}
