package com.abdullahturhan.reservation.dto;

import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@SuperBuilder
@NoArgsConstructor
public class FindOneReservationResponse {
    private Long reservationId;
    private String hotelName;
    private Double price;
    private Room room;
    private RoomType roomType;
    private LocalDateTime creationAt;
    private String firstName;
    private String lastName;
    private Integer stayDuration;
}
