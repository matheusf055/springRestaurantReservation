package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
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

    @CreatedDate
    private LocalDateTime reservationTime;

    private int numberOfPeople;

    private Status status;

    public enum Status{
        PENDING,
        RESEVERD
    }
}