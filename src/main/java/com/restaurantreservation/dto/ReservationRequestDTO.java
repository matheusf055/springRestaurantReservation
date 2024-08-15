package com.restaurantreservation.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReservationRequestDTO {

    private String customerId;
    private String restaurantId;
    private int numberOfPeople;
}
