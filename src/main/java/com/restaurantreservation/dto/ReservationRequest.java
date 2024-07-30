package com.restaurantreservation.dto;

import com.restaurantreservation.entity.Reservation;

public class ReservationRequest {

    private String usuarioId;
    private String restaurantId;
    private int numberOfPeople;
    private Reservation.Status status;
}
