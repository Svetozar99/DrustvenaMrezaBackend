package com.example.PraksaDrustvenaMreza.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.PraksaDrustvenaMreza.dtos.*;
import com.example.PraksaDrustvenaMreza.model.*;
import com.example.PraksaDrustvenaMreza.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PraksaDrustvenaMreza.service.CommentServiceInterface;

@Service
public class CommentService implements CommentServiceInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentDTO getOne(Long id) {
        Comment c = commentRepository.findOneById(id);

        return new CommentDTO(c);
    }

    @Override
    public CommentDTO save(AddCommentDTO commentDTO, String userName) {
        Comment comment = new Comment();
        Comment parentComment;
        if(commentDTO.getParentComment() != null)
             parentComment = commentRepository.findOneById(commentDTO.getParentComment());
        else
            parentComment = null;
        Post post = postRepository.findOneById(commentDTO.getPostId());
        User user = userRepository.findOneByUserName(userName);


        comment.setBodyComment(commentDTO.getBodyComment());
        if(parentComment != null)
            comment.setParentComment(parentComment.getId());
        else
            comment.setParentComment(null);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);

        comment = commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO, Long id) {
        Comment comment = commentRepository.findOneById(id);

        comment.setBodyComment(commentDTO.getBodyComment());
        comment.setCreatedAt(LocalDateTime.now());

        comment = commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDTO> findByPost(Long id) {
        List<Comment> comments = commentRepository.findByPost_id(id);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment c:comments){
            commentDTOS.add(new CommentDTO(c));
        }
        return commentDTOS;
    }
}
