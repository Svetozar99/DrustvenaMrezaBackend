package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;
import com.example.PraksaDrustvenaMreza.model.Post;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Long id;

    private String body;

    private UserDTO userDTO;

    public PostDTO(Post p){
        this(p.getId(), p.getBody(), new UserDTO(p.getUser()));
    }
}
