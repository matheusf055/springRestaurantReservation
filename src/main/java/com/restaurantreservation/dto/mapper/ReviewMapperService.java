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
        return modelMapper.map(reviewRequestDTO, Review.class);
    }

    public ReviewResponseDTO toResponseDTO(Review review){
        return modelMapper.map(review, ReviewResponseDTO.class);
    }
}
