package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.RoomType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReservationRoomTypeRequest {
    @NotBlank
    private RoomType roomType;
}
