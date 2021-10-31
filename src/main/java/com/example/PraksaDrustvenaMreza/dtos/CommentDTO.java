package com.example.PraksaDrustvenaMreza.dtos;

import com.example.PraksaDrustvenaMreza.model.Comment;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private Long id;

    private Long parentComment;

    private String bodyComment;

    private PostDTO postDTO;

    private UserDTO userDTO;

    public CommentDTO(Comment c){
        this(c.getId(), c.getParentComment(), c.getBodyComment(), new PostDTO(c.getPost()), new UserDTO(c.getUser()));
    }
}
