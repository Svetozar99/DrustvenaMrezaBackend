package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.AddCommentDTO;
import com.example.PraksaDrustvenaMreza.dtos.CommentDTO;

public interface CommentServiceInterface {

    public CommentDTO getOne(Long id);

    public CommentDTO save(AddCommentDTO commentDTO);

    public CommentDTO update(CommentDTO commentDTO, Long id);

    public void delete(Long id);
}
