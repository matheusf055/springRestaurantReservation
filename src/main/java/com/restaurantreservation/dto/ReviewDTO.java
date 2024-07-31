package com.restaurantreservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReviewDTO {

    private String Id;
    private String customerId;
    private String restauranteId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
