package com.example.PraksaDrustvenaMreza.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtDTO {

    private String value;

    private Long idUser;
}
