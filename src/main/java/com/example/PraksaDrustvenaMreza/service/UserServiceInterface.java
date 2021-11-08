package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.UserDTO;

import java.util.List;

public interface UserServiceInterface {

    public UserDTO getOne(Long id);

    public UserDTO save(UserDTO userDTO) throws Exception;

    public UserDTO update(UserDTO userDTO, Long id);

    public UserDTO getOneByUserName(String userName);

    public void delete(Long id);

    public List<UserDTO> search(String value);
}
