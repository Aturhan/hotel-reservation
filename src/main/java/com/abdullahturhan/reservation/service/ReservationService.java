package com.abdullahturhan.reservation.service;

import com.abdullahturhan.reservation.dto.*;
import com.abdullahturhan.reservation.entity.Customer;
import com.abdullahturhan.reservation.entity.Reservation;
import com.abdullahturhan.reservation.exception.CustomerNotFoundException;
import com.abdullahturhan.reservation.exception.ReservationNotFoundException;
import com.abdullahturhan.reservation.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;


    public ReservationService(ReservationRepository reservationRepository, CustomerService customerService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
    }
    @Transactional
    public CreateReservationResponse createOneReservation(CreateReservationRequest request){
            Optional<Customer> optionalCustomer =customerService.findCustomerById(request.getCustomerId());
            if(optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
               final Reservation reservation = Reservation.builder()
                       .customer(customer)
                       .stayDuration(request.getStayDuration())
                       .room(request.getRoom())
                       .roomType(request.getRoomType())
                       .build();
               final Reservation reservationFromDb = reservationRepository.save(reservation);
               return CreateReservationResponse.builder()
                       .reservationId(reservationFromDb.getId())
                       .hotelName(reservationFromDb.getHotelName())
                       .creationAt(reservationFromDb.getCreatedAt())
                       .roomType(reservationFromDb.getRoomType())
                       .room(reservationFromDb.getRoom())
                       .stayDuration(reservation.getStayDuration())
                       .price(reservationFromDb.getPrice())
                       .firstName(reservationFromDb.getCustomer().getFirstName())
                       .lastName(reservationFromDb.getCustomer().getLastName())
                       .age(reservationFromDb.getCustomer().getAge())
                       .email(reservationFromDb.getCustomer().getEmail())
                       .gender(reservationFromDb.getCustomer().getGender())
                       .build();
            }else {
                throw new CustomerNotFoundException("Customer not found");
            }

    }

    public FindOneReservationResponse findOneReservation(Long id){
        Optional<Reservation> checkReservation = reservationRepository.findById(id);
        if (checkReservation.isPresent()){
            return FindOneReservationResponse.builder()
                    .reservationId(checkReservation.get().getId())
                    .hotelName(checkReservation.get().getHotelName())
                    .price(checkReservation.get().getPrice())
                    .firstName(checkReservation.get().getCustomer().getFirstName())
                    .lastName(checkReservation.get().getCustomer().getLastName())
                    .stayDuration(checkReservation.get().getStayDuration())
                    .room(checkReservation.get().getRoom())
                    .roomType(checkReservation.get().getRoomType())
                    .creationAt(checkReservation.get().getCreatedAt())
                    .build();
        }else {
            throw new ReservationNotFoundException("Reservation not found");
        }
    }
    @Transactional
    public UpdateReservationResponse updateAllPartitions(Long id, UpdateReservationRequest request){
        Optional<Reservation> reservationFromDb = reservationRepository.findById(id);
        if(reservationFromDb.isPresent()){
            Reservation reservation = reservationFromDb.get();
            reservation.setRoom(request.getRoom());
            reservation.setRoomType(request.getRoomType());
            reservation.setStayDuration(request.getStayDuration());
           final Reservation updatedReservation =  reservationRepository.save(reservation);
            return UpdateReservationResponse.builder()
                    .reservationId(updatedReservation.getId())
                    .hotelName(updatedReservation.getHotelName())
                    .price(updatedReservation.getPrice())
                    .room(updatedReservation.getRoom())
                    .roomType(updatedReservation.getRoomType())
                    .stayDuration(updatedReservation.getStayDuration())
                    .updatedAt(updatedReservation.getUpdatedAt())
                    .build();

        }
         throw new ReservationNotFoundException("Reservation not found");
    }
    @Transactional
    public UpdateReservationResponse updateRoomNumber(Long id, UpdateReservationRoomNumberRequest request){
        Optional<Reservation> reservationFromDb = reservationRepository.findById(id);
        if (reservationFromDb.isPresent()){
            Reservation reservation = reservationFromDb.get();
            reservation.setRoom(request.getRoom());
            final Reservation updatedReservation =  reservationRepository.save(reservation);
            return UpdateReservationResponse.builder()
                    .reservationId(updatedReservation.getId())
                    .hotelName(updatedReservation.getHotelName())
                    .price(updatedReservation.getPrice())
                    .room(updatedReservation.getRoom())
                    .roomType(updatedReservation.getRoomType())
                    .stayDuration(updatedReservation.getStayDuration())
                    .updatedAt(updatedReservation.getUpdatedAt())
                    .build();
        }
        throw new ReservationNotFoundException("Reservation not found");
    }
    @Transactional
    public UpdateReservationResponse updateRoomType(Long id, UpdateReservationRoomTypeRequest request){
        Optional<Reservation> reservationFromDb = reservationRepository.findById(id);
        if (reservationFromDb.isPresent()) {
            Reservation reservation = reservationFromDb.get();
            reservation.setRoomType(request.getRoomType());
            final Reservation updatedReservation =  reservationRepository.save(reservation);
            return UpdateReservationResponse.builder()
                    .reservationId(updatedReservation.getId())
                    .hotelName(updatedReservation.getHotelName())
                    .price(updatedReservation.getPrice())
                    .room(updatedReservation.getRoom())
                    .roomType(updatedReservation.getRoomType())
                    .stayDuration(updatedReservation.getStayDuration())
                    .updatedAt(updatedReservation.getUpdatedAt())
                    .build();
        }
        throw new ReservationNotFoundException("Reservation not found");
    }
    @Transactional
    public UpdateReservationResponse updateStayDuration(Long id, UpdateReservationStayDurationRequest request){
        Optional<Reservation> reservationFromDb = reservationRepository.findById(id);
        if (reservationFromDb.isPresent()){
            Reservation reservation = reservationFromDb.get();
            reservation.setStayDuration(request.getStayDuration());
            final Reservation updatedReservation =  reservationRepository.save(reservation);
            return UpdateReservationResponse.builder()
                    .reservationId(updatedReservation.getId())
                    .hotelName(updatedReservation.getHotelName())
                    .price(updatedReservation.getPrice())
                    .room(updatedReservation.getRoom())
                    .roomType(updatedReservation.getRoomType())
                    .stayDuration(updatedReservation.getStayDuration())
                    .updatedAt(updatedReservation.getUpdatedAt())
                    .build();
        }
        throw new ReservationNotFoundException("Reservation not found");
    }
    @Transactional
    public void deleteOneReservation(Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(reservationRepository::delete);
    }


}
