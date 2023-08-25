package com.abdullahturhan.reservation.entity;

import com.abdullahturhan.reservation.enumTypes.Room;
import com.abdullahturhan.reservation.enumTypes.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private final String hotelName = "MyHotel";

    private Double price;


    private Integer stayDuration;

    @Enumerated
    private Room room;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @PrePersist
    public void calculatePrice() {
        if (getStayDuration() != null && getStayDuration() > 0 ) {
            switch (getRoomType().getValue()) {
                case "Single" -> setPrice((5000.00) * getStayDuration());
                case "Double" -> setPrice((10000.00) * getStayDuration());
                case "Suite" -> setPrice((15000.00) * getStayDuration());
            }

        }
    }

    }



