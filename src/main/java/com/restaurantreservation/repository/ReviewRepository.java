package com.restaurantreservation.repository;

import com.restaurantreservation.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
