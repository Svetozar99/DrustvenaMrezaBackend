package com.example.PraksaDrustvenaMreza.dtos;
import com.example.PraksaDrustvenaMreza.model.Comment;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddCommentDTO {

    private Long id;

    private Long postId;

    private Long parentComment;

    private String bodyComment;

    public AddCommentDTO(Comment c){
        this(c.getId(), c.getPost().getId(), c.getParentComment(), c.getBodyComment());
    }
}