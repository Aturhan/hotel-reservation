package com.abdullahturhan.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class SignInRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
