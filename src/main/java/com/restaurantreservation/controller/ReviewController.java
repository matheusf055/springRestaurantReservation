package com.restaurantreservation.controller;

import com.restaurantreservation.dto.ReviewRequestDTO;
import com.restaurantreservation.dto.ReviewResponseDTO;
import com.restaurantreservation.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Endpoints for Review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "Creates a review", description = "Creates a review", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(schema = @Schema(implementation = ReviewResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<ReviewResponseDTO> create(@RequestBody ReviewRequestDTO reviewRequestDTO){
        ReviewResponseDTO reviewResponseDTO = reviewService.create(reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Find all reviews", description = "Find all reviews", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDTO.class))
            )),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReviewResponseDTO>> findAll(){
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("restaurant/{restaurantId}")
    @Operation(summary = "Finds a review by restaurant id", description = "Find a review by restaurant id", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReviewResponseDTO>> findByRestaurantId(@PathVariable String restaurantId){
        List<ReviewResponseDTO> review = reviewService.findReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Finds a review by customer id", description = "Find a review by customer id", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReviewResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReviewResponseDTO>> findByCustomerId(@PathVariable String customerId){
        List<ReviewResponseDTO> review = reviewService.findReviewsByCustomerId(customerId);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a review", description = "Updates a review", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ReviewResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable String id, @RequestBody ReviewRequestDTO reviewRequestDTO){
        ReviewResponseDTO reviewResponseDTO = reviewService.update(id, reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a review", description = "Deletes a review", tags = {"Reviews"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable String id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
