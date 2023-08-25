package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Room;
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
public class UpdateReservationRequest {
    @NotBlank
    private Room room;
    @NotBlank
    private RoomType roomType;
    @NotBlank
    private Integer stayDuration;
}
