package com.restaurantreservation.dto;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class CustomerDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;
}
