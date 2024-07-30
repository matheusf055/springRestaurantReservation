package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "tb_review")
@AllArgsConstructor @NoArgsConstructor @Data
public class Review {

    @MongoId
    private String id;

    @Indexed(name = "usuario_id_index")
    private String usuarioId;

    @Indexed(name = "restaurant_id_index")
    private String restaurantId;
    private LocalDateTime reservationTime;
    private int numberOfPeople;
    private String status;
}
