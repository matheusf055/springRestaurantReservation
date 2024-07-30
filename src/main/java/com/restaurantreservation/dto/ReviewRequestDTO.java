package com.restaurantreservation.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class ReviewRequestDTO {

    private String usuarioId;
    private String restaurantId;
    private int rating;
    private String comment;
}
