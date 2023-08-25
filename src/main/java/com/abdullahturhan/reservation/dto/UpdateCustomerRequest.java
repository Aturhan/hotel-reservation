package com.abdullahturhan.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String LastName;
}
