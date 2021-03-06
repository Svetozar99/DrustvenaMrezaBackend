package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;
import java.time.LocalDateTime;
import com.example.PraksaDrustvenaMreza.model.RequestForFollowing;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestDTO {

    private Long id;

    private LocalDateTime sendAt;

    private UserDTO sender;

    private UserDTO receiver;

    public RequestDTO(RequestForFollowing r){
        this(r.getId(), r.getCreatedAt(), new UserDTO(r.getSender()), new UserDTO(r.getReceiver()));
    }
}
