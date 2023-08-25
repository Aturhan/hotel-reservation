package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Gender;
import com.abdullahturhan.reservation.enumTypes.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private Integer age;
    @NotBlank
    private Gender gender;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    private Role role;
}
