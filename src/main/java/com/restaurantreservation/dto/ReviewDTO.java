package com.restaurantreservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReviewDTO {

    private String id;
    private String customerId;
    private String restaurantId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
