package com.restaurantreservation.services;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.dto.mapper.RestaurantMapperService;
import com.restaurantreservation.entity.Restaurant;
import com.restaurantreservation.entity.Review;
import com.restaurantreservation.repository.RestaurantRepository;
import com.restaurantreservation.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantMapperService restaurantMapperService;

    public RestaurantResponseDTO create(RestaurantRequestDTO restaurantRequestDTO){
        Restaurant restaurant = restaurantMapperService.toEntity(restaurantRequestDTO);
        Restaurant createdRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapperService.toResponseDTO(createdRestaurant);
    }

    public List<RestaurantResponseDTO> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map(restaurant -> {
            List<Review> reviews = reviewRepository.findByRestaurantId(restaurant.getId());
            restaurant.setReviews(reviews);
            return restaurantMapperService.toResponseDTO(restaurant);
        }).collect(Collectors.toList());
    }

    public Optional<RestaurantResponseDTO> findById(String id){
        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    List<Review> reviews = reviewRepository.findByRestaurantId(id);
                    restaurant.setReviews(reviews);
                    return restaurantMapperService.toResponseDTO(restaurant);
                });
    }

    public RestaurantResponseDTO update(String id, RestaurantRequestDTO restaurantRequestDTO){
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));

        Restaurant updatedRestaurant = restaurantMapperService.toEntity(restaurantRequestDTO);
        updatedRestaurant.setId(existingRestaurant.getId());

        Restaurant savedRestaurant = restaurantRepository.save(updatedRestaurant);
        return restaurantMapperService.toResponseDTO(savedRestaurant);
    }

    public void deleteById(String id){
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
        restaurantRepository.delete(existingRestaurant);
    }
}
