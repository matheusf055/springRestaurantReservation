package com.restaurantreservation.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Data
public class RestaurantRequestDTO {

    private String name;
    private String Location;
    private String kindOfFood;
}
