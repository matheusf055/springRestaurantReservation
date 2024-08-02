package com.restaurantreservation.controller;

import com.restaurantreservation.dto.ReviewRequestDTO;
import com.restaurantreservation.dto.ReviewResponseDTO;
import com.restaurantreservation.services.CustomerService;
import com.restaurantreservation.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(@RequestBody ReviewRequestDTO reviewRequestDTO){
        ReviewResponseDTO reviewResponseDTO = reviewService.create(reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> findAll(){
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewResponseDTO>> findByRestaurantId(@PathVariable String restaurantId){
        List<ReviewResponseDTO> review = reviewService.findReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewResponseDTO>> findByCustomerId(@PathVariable String customerId){
        List<ReviewResponseDTO> review = reviewService.findReviewsByCustomerId(customerId);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable String id, @RequestBody ReviewRequestDTO reviewRequestDTO){
        ReviewResponseDTO reviewResponseDTO = reviewService.update(id, reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
