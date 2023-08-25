package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Room;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReservationRoomNumberRequest {
    @NotBlank
    private Room room;
}
