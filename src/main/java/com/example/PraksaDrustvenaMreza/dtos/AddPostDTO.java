package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;
import com.example.PraksaDrustvenaMreza.model.Post;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPostDTO {

    private Long id;

    private String body;

    public AddPostDTO(Post p){
        this(p.getId(), p.getBody());
    }
}
