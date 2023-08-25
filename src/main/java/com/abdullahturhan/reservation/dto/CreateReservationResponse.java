package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Gender;
import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public class CreateReservationResponse {
    private Long reservationId;
    private String hotelName;
    private Double price;
    private Room room;
    private Integer stayDuration;
    private RoomType roomType;
    private LocalDateTime creationAt;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String email;
}
