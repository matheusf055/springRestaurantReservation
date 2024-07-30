package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "tb_restaurants")
@AllArgsConstructor @NoArgsConstructor @Data
public class Restaurant {

    @MongoId
    private String id;
    private String name;
    private String location;
    private String kindOfFood;

    @CreatedDate
    private LocalDateTime createdAt;
    private List<Review> reviews;
}
