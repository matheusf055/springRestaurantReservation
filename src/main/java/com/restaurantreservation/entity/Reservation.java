package com.restaurantreservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "tb_reservation")
@AllArgsConstructor @NoArgsConstructor @Data
public class Reservation {

    @MongoId
    private String id;

    private String customerId;

    private String restaurantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime reservationTime;

    private int numberOfPeople;

    private Status status = Status.PENDING;

    public enum Status {
        PENDING,
        RESERVED
    }
}