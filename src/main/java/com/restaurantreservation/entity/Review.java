package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "td_reservation")
@AllArgsConstructor @NoArgsConstructor @Data
public class Review {

    @MongoId
    private String Id;

    @Indexed(name = "usuario_id_index")
    private String usuarioId;

    @Indexed(name = "restaurant_id_index")
    private String restauranteId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
