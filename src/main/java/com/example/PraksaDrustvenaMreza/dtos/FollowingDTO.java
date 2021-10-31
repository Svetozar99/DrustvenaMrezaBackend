package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;
import java.time.LocalDateTime;
import com.example.PraksaDrustvenaMreza.model.Following;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FollowingDTO {

    private Long id;

    private LocalDateTime sendAt;

    private UserDTO sender;

    private UserDTO receiver;

    public FollowingDTO(Following f){
        this(f.getId(), f.getCreatedAt(), new UserDTO(f.getFollowee()), new UserDTO(f.getFollower()));
    }
}
