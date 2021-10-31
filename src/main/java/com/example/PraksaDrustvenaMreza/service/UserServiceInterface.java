package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.UserDTO;

public interface UserServiceInterface {

    public UserDTO getOne(Long id);

    public UserDTO save(UserDTO userDTO) throws Exception;

    public UserDTO update(UserDTO userDTO, Long id);

    public UserDTO getOneByUserName(String userName);

    public void delete(Long id);
}
