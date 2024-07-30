package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "tb_review")
@AllArgsConstructor @NoArgsConstructor @Data
public class Review {

    @MongoId
    private String Id;

    private String customerId;

    private String restauranteId;

    private int rating;

    private String comment;

    @CreatedDate
    private LocalDateTime ratingTime;
}
