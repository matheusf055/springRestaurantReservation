package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tb_review")
@AllArgsConstructor @NoArgsConstructor @Data
public class Review {

    @Id
    private String id;
    private String UsuarioId;
    private String restaurantId;
    private LocalDateTime reservationTime;
    private int numberOfPeople;
    private String status;
}
