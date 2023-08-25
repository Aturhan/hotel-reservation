package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateReservationResponse {
    private Long reservationId;
    private String hotelName;
    private Double price;
    private Room room;
    private Integer stayDuration;
    private RoomType roomType;
    private LocalDateTime updatedAt;

}
