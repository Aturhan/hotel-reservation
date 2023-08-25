package com.abdullahturhan.reservation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class JwtAuthenticationResponse {
    private String token;
}
