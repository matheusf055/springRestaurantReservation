package com.restaurantreservation.repository;

import com.restaurantreservation.dto.CustomerDTO;
import com.restaurantreservation.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByRestaurantId(String restaurantId);

    List<CustomerDTO> findReviewsByCustomerId(String customerId);
}
