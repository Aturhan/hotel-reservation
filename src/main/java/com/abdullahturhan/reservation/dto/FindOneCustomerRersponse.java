package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Gender;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class FindOneCustomerRersponse {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private LocalDateTime createdAt;
}
