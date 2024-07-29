package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "td_reservation")
@AllArgsConstructor @NoArgsConstructor @Data
public class Reservation {

    @Id
    private String Id;
    private String usuarioId;
    private String restauranteId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
