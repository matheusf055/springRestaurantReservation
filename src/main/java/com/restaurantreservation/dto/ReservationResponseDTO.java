package com.restaurantreservation.dto;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReservationResponseDTO {

    private String id;
    private String CustomerId;
    private String RestaurantId;
    private LocalDateTime reservationTime;
    private int numberOfPeople;
    private Status status;

    public enum Status {
        PENDING,
        RESERVED
    }
}
