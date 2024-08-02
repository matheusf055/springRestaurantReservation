package com.restaurantreservation.dto.mapper;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.dto.ReviewDTO;
import com.restaurantreservation.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapperService {

    public Restaurant toEntity(RestaurantRequestDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setKindOfFood(dto.getKindOfFood());
        return restaurant;
    }

    public RestaurantResponseDTO toResponseDTO(Restaurant restaurant) {
        RestaurantResponseDTO dto = new RestaurantResponseDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setLocation(restaurant.getLocation());
        dto.setKindOfFood(restaurant.getKindOfFood());
        dto.setCreatedAt(restaurant.getCreatedAt());
        dto.setReviews(restaurant.getReviews().stream()
                .map(review -> new ReviewDTO(review.getId(), review.getCustomerId(), review.getRestaurantId() ,review.getRating(), review.getComment(), review.getRatingTime()))
                .toList());
        return dto;
    }
}
