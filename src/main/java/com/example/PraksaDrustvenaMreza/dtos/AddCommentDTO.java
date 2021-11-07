package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;
import com.example.PraksaDrustvenaMreza.model.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddCommentDTO {

    private Long id;

    private Long postId;

    private String bodyComment;

    private CommentDTO commentDTO;

    public AddCommentDTO(Comment c){
        this(c.getId(), c.getPost().getId(), c.getBodyComment(), new CommentDTO(c.getComment()));
    }
}