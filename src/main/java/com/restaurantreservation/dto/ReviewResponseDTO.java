package com.restaurantreservation.dto;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    private String id;
    private String customerId;
    private String restaurantId;
    private int rating;
    private String comment;
    private LocalDateTime ratingTime;
}
