package com.restaurantreservation.dto;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class CustomerResponseDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
}
