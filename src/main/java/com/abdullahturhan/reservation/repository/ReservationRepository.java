package com.abdullahturhan.reservation.repository;

import com.abdullahturhan.reservation.entity.Reservation;
import com.abdullahturhan.reservation.enumTypes.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
  Optional<Reservation> findByRoom(Room room);
}
