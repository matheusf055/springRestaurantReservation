package com.restaurantreservation.dto;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor @NoArgsConstructor @Data
public class CustomerResponseDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;
}
