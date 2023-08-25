package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerResponse {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String email;
    private LocalDateTime updatedAt;
}
