package com.restaurantreservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReservationRequestDTO {

    private String customerId;
    private String restaurantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime reservationTime;
    private int numberOfPeople;
}
