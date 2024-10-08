package com.restaurantreservation.dto;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReviewResponseDTO {

    private String id;
    private String customerId;
    private String restaurantId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
