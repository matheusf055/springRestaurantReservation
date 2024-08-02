package com.restaurantreservation.services;

import com.restaurantreservation.dto.CustomerDTO;
import com.restaurantreservation.dto.ReviewDTO;
import com.restaurantreservation.dto.ReviewRequestDTO;
import com.restaurantreservation.dto.ReviewResponseDTO;
import com.restaurantreservation.dto.mapper.ReviewMapperService;
import com.restaurantreservation.entity.Review;
import com.restaurantreservation.repository.CustomerRepository;
import com.restaurantreservation.repository.RestaurantRepository;
import com.restaurantreservation.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;
    private final ReviewMapperService reviewMapperService;
    private final ModelMapper modelMapper;

    public ReviewResponseDTO create(ReviewRequestDTO reviewRequestDTO){
        String restaurantId = reviewRequestDTO.getRestaurantId();
        boolean restaurantExists = restaurantRepository.existsById(restaurantId);

        String usuarioId = reviewRequestDTO.getCustomerId();
        boolean customerExists = customerRepository.existsById(usuarioId);

        if (!restaurantExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
        }

        if (!customerExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        Review review = reviewMapperService.toEntity(reviewRequestDTO);
        Review createdReview = reviewRepository.save(review);
        return reviewMapperService.toResponseDTO(createdReview);
    }

    public List<ReviewResponseDTO> findAll(){
        return reviewRepository.findAll().stream().map(reviewMapperService::toResponseDTO).toList();
    }

   public List<ReviewResponseDTO> findReviewsByRestaurantId(String restaurantId){
       if (!restaurantRepository.existsById(restaurantId)) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
       }

       List<ReviewDTO> reviews = reviewRepository.findByRestaurantId(restaurantId);

       return reviews.stream()
               .map(review -> modelMapper.map(review, ReviewResponseDTO.class))
               .collect(Collectors.toList());
   }


    public List<ReviewResponseDTO> findReviewsByCustomerId(String customerId){
        if (!customerRepository.existsById(customerId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        List<CustomerDTO> customers = reviewRepository.findReviewsByCustomerId(customerId);

        return customers.stream()
                .map(customer -> modelMapper.map(customer, ReviewResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO update(String id, ReviewRequestDTO reviewRequestDTO){
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        Review updatedReview = reviewMapperService.toEntity(reviewRequestDTO);
        updatedReview.setId(existingReview.getId());

        Review savedReview = reviewRepository.save(updatedReview);
        return reviewMapperService.toResponseDTO(savedReview);
    }

    public void deleteById(String id){
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        reviewRepository.delete(existingReview);
    }

}
