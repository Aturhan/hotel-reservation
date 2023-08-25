package com.abdullahturhan.reservation.controller;

import com.abdullahturhan.reservation.dto.*;
import com.abdullahturhan.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("")
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.createOneReservation(request));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindOneReservationResponse> findOneReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(reservationService.findOneReservation(id));
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateReservationResponse> updateAllPartitions(@PathVariable Long id,
                                                                         @RequestBody UpdateReservationRequest request){

        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.updateAllPartitions(id,request));
    }
    @PutMapping(path = "/update-room-number/{id}")
    public ResponseEntity<UpdateReservationResponse> updateRoomNumber(@PathVariable Long id,
                                                                      @RequestBody UpdateReservationRoomNumberRequest request){

        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.updateRoomNumber(id,request));
    }
    @PutMapping(path = "update-room-type/{id}")
    public ResponseEntity<UpdateReservationResponse> updateRoomType(@PathVariable Long id,
                                                                        @RequestBody UpdateReservationRoomTypeRequest request){

        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.updateRoomType(id,request));
    }
    @PutMapping(path = "/update-stay-duration/{id}")
    public ResponseEntity<UpdateReservationResponse> updateStayDuration(@PathVariable Long id,
                                                                    @RequestBody UpdateReservationStayDurationRequest request){

        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.updateStayDuration(id,request));
    }
    @DeleteMapping(path = "/{id}")
    public void deleteOneReservation(@PathVariable Long id){
        reservationService.deleteOneReservation(id);
    }

}
