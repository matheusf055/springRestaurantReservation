package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tb_restaurants")
@AllArgsConstructor @NoArgsConstructor @Data
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String location;
    private String kindOfFood;
    private List<Review> reviews;
}
