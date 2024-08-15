package com.restaurantreservation.dto.mapper;

import com.restaurantreservation.dto.ReviewRequestDTO;
import com.restaurantreservation.dto.ReviewResponseDTO;
import com.restaurantreservation.entity.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapperService {

    private final ModelMapper modelMapper;

    public Review toEntity(ReviewRequestDTO reviewRequestDTO){
        Review review = new Review();
        review.setCustomerId(reviewRequestDTO.getCustomerId());
        review.setRestaurantId(reviewRequestDTO.getRestaurantId());
        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());
        return review;
    }

    public ReviewResponseDTO toResponseDTO(Review review){
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setCustomerId(review.getCustomerId());
        dto.setRestaurantId(review.getRestaurantId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setRatingTime(review.getRatingTime());
        return dto;
    }
}
