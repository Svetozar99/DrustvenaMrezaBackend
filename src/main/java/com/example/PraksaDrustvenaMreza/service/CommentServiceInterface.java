package com.example.PraksaDrustvenaMreza.service;

import com.example.PraksaDrustvenaMreza.dtos.*;

import java.util.List;

public interface CommentServiceInterface {

    public CommentDTO getOne(Long id);

    public CommentDTO save(AddCommentDTO commentDTO, String userName);

    public CommentDTO addCommentOnComment(AddCommentOnCommentDTO commentDTO, String userName);

    public CommentDTO update(CommentDTO commentDTO, Long id);

    public void delete(Long id);

    public List<CommentDTO> findByPost(Long id);
}
