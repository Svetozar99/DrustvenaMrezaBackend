package com.example.PraksaDrustvenaMreza.dtos;

import com.example.PraksaDrustvenaMreza.model.User;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    private LocalDateTime registredAt;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String repeatedPassword;

    public UserDTO(User u){
        this(u.getId(), u.getCreatedAt(), u.getFirstName(),u.getLastName(),
                u.getUserName(), u.getPassword(), u.getRepeatedPassword());
    }
}