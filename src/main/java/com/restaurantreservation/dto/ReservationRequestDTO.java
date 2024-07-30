package com.restaurantreservation.dto;

import com.restaurantreservation.entity.Reservation;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReservationRequestDTO {

    private String usuarioId;
    private String restaurantId;
    private int numberOfPeople;
    private Reservation.Status status;
}
