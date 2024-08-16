package com.restaurantreservation.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tb_restaurants")
@AllArgsConstructor @NoArgsConstructor @Data
public class Restaurant {

    @MongoId
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotBlank
    private String kindOfFood;

    @CreatedDate
    private LocalDateTime createdAt;

    private List<Review> reviews = new ArrayList<>();
}
