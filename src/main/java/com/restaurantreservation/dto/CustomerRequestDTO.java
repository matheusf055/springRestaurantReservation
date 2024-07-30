package com.restaurantreservation.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class CustomerRequestDTO {

    private String name;
    private String email;
    private String phoneNumber;
}
