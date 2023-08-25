package com.abdullahturhan.reservation.service;

import com.abdullahturhan.reservation.dto.CreateReservationRequest;
import com.abdullahturhan.reservation.dto.CreateReservationResponse;
import com.abdullahturhan.reservation.dto.FindOneReservationResponse;
import com.abdullahturhan.reservation.dto.SignUpRequest;
import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.entity.Reservation;
import com.abdullahturhan.reservation.enumTypes.Role;
import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import com.abdullahturhan.reservation.repository.ReservationRepository;
import org.apache.el.stream.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles(value = "integration")
class ReservationServiceTest {

    private ReservationService reservationService;

    private ReservationRepository reservationRepository;
    private  CustomerService customerService;

    @BeforeEach
    void setUp(){
        reservationRepository = Mockito.mock(ReservationRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        reservationService = new ReservationService(reservationRepository,customerService);

    }


    @Test
    void itShouldReturnReservationByGivenId_WhenReservationExist() {
        //given
        Long id = 1L;

        FindOneReservationResponse response = FindOneReservationResponse.builder()
                .reservationId(id)
                .room(Room.ROOM_1)
                .roomType(RoomType.SINGLE)
                .build();

        Reservation expected = Reservation.builder()
                .id(id)
                .room(Room.ROOM_1)
                .roomType(RoomType.SINGLE)
                .build();



        //when
        when(reservationService.findOneReservation(id)).thenReturn(response);

        Reservation actual = expected;

        //then
        verify(reservationRepository).findById(id);
        assertEquals(expected,actual);

    }


}