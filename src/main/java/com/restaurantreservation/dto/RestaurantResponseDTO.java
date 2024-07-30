package com.restaurantreservation.dto;

import com.restaurantreservation.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantResponseDTO {

    private String id;
    private String name;
    private String location;
    private String kindOfFood;
    private LocalDateTime createdAt;
    private List<Review> reviews;
}
