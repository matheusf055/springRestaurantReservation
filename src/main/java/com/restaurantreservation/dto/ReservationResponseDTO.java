package com.restaurantreservation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReservationResponseDTO {

    private String id;
    private String customerId;
    private String restaurantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime reservationTime;
    private int numberOfPeople;
    private Status status;

    public enum Status {
        PENDING,
        RESERVED
    }
}
