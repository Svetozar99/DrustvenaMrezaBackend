package com.example.PraksaDrustvenaMreza.dtos;

import com.sun.istack.Nullable;
import lombok.*;
import com.example.PraksaDrustvenaMreza.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private Long id;

    private String bodyComment;

    private List<CommentDTO> commentDTOS;

    private UserDTO userDTO;

    private static ArrayList<CommentDTO> convertDto(List<Comment> comments1){
        ArrayList<CommentDTO> commDTOS = new ArrayList<>();
        for(Comment c: comments1){
            commDTOS.add(new CommentDTO(c));
        }
        return commDTOS;
    }

    public CommentDTO(Comment c){
        this(c.getId(), c.getBodyComment() ,convertDto(c.getComments()), new UserDTO(c.getUser()));
    }
}
