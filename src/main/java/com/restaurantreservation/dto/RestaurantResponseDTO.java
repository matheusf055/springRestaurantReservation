package com.restaurantreservation.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data
public class RestaurantResponseDTO {

    private String id;
    private String name;
    private String location;
    private String kindOfFood;
    private LocalDateTime createdAt;
    private List<ReviewDTO> reviews;
}
