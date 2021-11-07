package com.example.PraksaDrustvenaMreza.dtos;

import com.example.PraksaDrustvenaMreza.model.Comment;
import lombok.*;
import com.example.PraksaDrustvenaMreza.model.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Long id;

    private String body;

    private UserDTO userDTO;

    private List<CommentDTO> comments;

    private static List<CommentDTO> convertDto(List<Comment> comments1){
        List<CommentDTO> commDTOS = new ArrayList<>();

        for (Comment comment : comments1) {
            if(comment.getComment() == null) {
                commDTOS.add(new CommentDTO(comment));
            }
        }

        return commDTOS;
    }

    public PostDTO(Post p){
        this(p.getId(), p.getBody(), new UserDTO(p.getUser()), convertDto(p.getComments()));
    }
}
