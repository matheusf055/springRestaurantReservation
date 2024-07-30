package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "td_review")
@AllArgsConstructor @NoArgsConstructor @Data
public class Review {

    @MongoId
    private String Id;

    @Indexed(name = "customer_id_index")
    private String customerId;

    @Indexed(name = "restaurant_id_index")
    private String restauranteId;
    private int rating;
    private String comment;

    @CreatedDate
    private LocalDateTime ratingTime;
}
