package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CreateReservationRequest {
    @NotBlank
    private Room room;
    @NotBlank
    private RoomType roomType;
    @NotBlank
    private Integer stayDuration;
    @NotBlank
    private Long customerId;

}
