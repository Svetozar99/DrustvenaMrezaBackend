package com.example.PraksaDrustvenaMreza.dtos;

import com.example.PraksaDrustvenaMreza.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddCommentOnCommentDTO {

    private Long id;

    private Long commentId;

    private String bodyComment;

    public AddCommentOnCommentDTO(Comment c){
        this(c.getId(), c.getComment().getId(), c.getBodyComment());
    }
}
