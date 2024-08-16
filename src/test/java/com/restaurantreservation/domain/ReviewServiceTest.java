package com.restaurantreservation.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.restaurantreservation.dto.ReviewRequestDTO;
import com.restaurantreservation.dto.ReviewResponseDTO;
import com.restaurantreservation.entity.Review;
import com.restaurantreservation.repository.CustomerRepository;
import com.restaurantreservation.repository.RestaurantRepository;
import com.restaurantreservation.repository.ReviewRepository;
import com.restaurantreservation.dto.mapper.ReviewMapperService;
import com.restaurantreservation.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ReviewMapperService reviewMapperService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReview_WithValidData_ReturnsResponseDTO() {
        ReviewRequestDTO requestDTO = new ReviewRequestDTO("customerId", "restaurantId", 5, "Great");
        Review review = new Review(null, "customerId", "restaurantId", 5, "Great", LocalDateTime.now());
        ReviewResponseDTO responseDTO = new ReviewResponseDTO("1", "customerId", "restaurantId", 5, "Great", LocalDateTime.now());

        when(restaurantRepository.existsById("restaurantId")).thenReturn(true);
        when(customerRepository.existsById("customerId")).thenReturn(true);
        when(reviewMapperService.toEntity(requestDTO)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);
        when(reviewMapperService.toResponseDTO(review)).thenReturn(responseDTO);

        ReviewResponseDTO result = reviewService.create(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(restaurantRepository).existsById("restaurantId");
        verify(customerRepository).existsById("customerId");
        verify(reviewMapperService).toEntity(requestDTO);
        verify(reviewRepository).save(review);
        verify(reviewMapperService).toResponseDTO(review);
    }

    @Test
    void createByRestaurantId_WithExistingId_ReturnsResponseDTO() {
        ReviewRequestDTO requestDTO = new ReviewRequestDTO("customerId", "restaurantId", 5, "Great");

        when(restaurantRepository.existsById("restaurantId")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.create(requestDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(restaurantRepository).existsById("restaurantId");
        verify(reviewMapperService, never()).toEntity(any(ReviewRequestDTO.class));
        verify(reviewRepository, never()).save(any(Review.class));
        verify(reviewMapperService, never()).toResponseDTO(any(Review.class));
    }

    @Test
    void creatByCustomerId_WithExistingId_ReturnsResponseDTO() {
        ReviewRequestDTO requestDTO = new ReviewRequestDTO("customerId", "restaurantId", 5, "Great");
        when(restaurantRepository.existsById("restaurantId")).thenReturn(true);
        when(customerRepository.existsById("customerId")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.create(requestDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(restaurantRepository).existsById("restaurantId");
        verify(customerRepository).existsById("customerId");
        verify(reviewMapperService, never()).toEntity(any(ReviewRequestDTO.class));
        verify(reviewRepository, never()).save(any(Review.class));
        verify(reviewMapperService, never()).toResponseDTO(any(Review.class));
    }

    @Test
    void findAll_ReturnsListOfReviewsResponseDto() {
        Review review = new Review("1", "customerId", "restaurantId", 5, "Great", LocalDateTime.now());
        ReviewResponseDTO responseDTO = new ReviewResponseDTO("1", "customerId", "restaurantId", 5, "Great", LocalDateTime.now());
        List<Review> reviews = List.of(review);

        when(reviewRepository.findAll()).thenReturn(reviews);
        when(reviewMapperService.toResponseDTO(review)).thenReturn(responseDTO);

        List<ReviewResponseDTO> result = reviewService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(reviewRepository).findAll();
        verify(reviewMapperService).toResponseDTO(review);
    }

    @Test
    void findByRestaurantId_WithExistingId_ReturnsResponseDTO() {
        String restaurantId = "restaurantId";
        Review review = new Review("1", "customerId", restaurantId, 5, "Great", LocalDateTime.now());
        ReviewResponseDTO responseDTO = new ReviewResponseDTO("1", "customerId", restaurantId, 5, "Great", LocalDateTime.now());
        List<Review> reviews = List.of(review);

        when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
        when(reviewRepository.findByRestaurantId(restaurantId)).thenReturn(reviews);
        when(modelMapper.map(review, ReviewResponseDTO.class)).thenReturn(responseDTO);

        List<ReviewResponseDTO> result = reviewService.findReviewsByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(restaurantRepository).existsById(restaurantId);
        verify(reviewRepository).findByRestaurantId(restaurantId);
        verify(modelMapper).map(review, ReviewResponseDTO.class);
    }

    @Test
    void findByCustomerId_WithExistingId_ReturnsResponseDTO() {
        String customerId = "customerId";
        Review review = new Review("1", customerId, "restaurantId", 5, "Great", LocalDateTime.now());
        ReviewResponseDTO responseDTO = new ReviewResponseDTO("1", customerId, "restaurantId", 5, "Great", LocalDateTime.now());
        List<Review> reviews = List.of(review);

        when(customerRepository.existsById(customerId)).thenReturn(true);
        when(reviewRepository.findReviewsByCustomerId(customerId)).thenReturn(reviews);
        when(modelMapper.map(review, ReviewResponseDTO.class)).thenReturn(responseDTO);

        List<ReviewResponseDTO> result = reviewService.findReviewsByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(customerRepository).existsById(customerId);
        verify(reviewRepository).findReviewsByCustomerId(customerId);
        verify(modelMapper).map(review, ReviewResponseDTO.class);
    }

    @Test
    void findByRestaurantId_WithExistingId_ReturnsNotFound() {
        String restaurantId = "restaurantId";

        when(restaurantRepository.existsById(restaurantId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.findReviewsByRestaurantId(restaurantId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(restaurantRepository).existsById(restaurantId);
        verify(reviewRepository, never()).findByRestaurantId(restaurantId);
    }

    @Test
    void findByCustomerId_WithExistingId_ReturnsNotFound() {
        String customerId = "customerId";

        when(customerRepository.existsById(customerId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.findReviewsByCustomerId(customerId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(customerRepository).existsById(customerId);
        verify(reviewRepository, never()).findReviewsByCustomerId(customerId);
    }

    @Test
    void updateReview_WithValidData_ReturnsResponseDTO() {
        String id = "1";
        ReviewRequestDTO requestDTO = new ReviewRequestDTO("customerId", "restaurantId", 5, "Updated");
        Review existingReview = new Review(id, "customerId", "restaurantId", 4, "Old comment", LocalDateTime.now());
        Review updatedReview = new Review(id, "customerId", "restaurantId", 5, "Updated", LocalDateTime.now());
        ReviewResponseDTO responseDTO = new ReviewResponseDTO(id, "customerId", "restaurantId", 5, "Updated", LocalDateTime.now());

        when(reviewRepository.findById(id)).thenReturn(Optional.of(existingReview));
        when(reviewMapperService.toEntity(requestDTO)).thenReturn(updatedReview);
        when(reviewRepository.save(updatedReview)).thenReturn(updatedReview);
        when(reviewMapperService.toResponseDTO(updatedReview)).thenReturn(responseDTO);

        ReviewResponseDTO result = reviewService.update(id, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(reviewRepository).findById(id);
        verify(reviewMapperService).toEntity(requestDTO);
        verify(reviewRepository).save(updatedReview);
        verify(reviewMapperService).toResponseDTO(updatedReview);
    }

    @Test
    void updateReview_WithInvalidData_ReturnsNotFound() {
        String id = "1";
        ReviewRequestDTO requestDTO = new ReviewRequestDTO("customerId", "restaurantId", 5, "Updated");

        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.update(id, requestDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(reviewRepository).findById(id);
        verify(reviewRepository, never()).save(any(Review.class));
        verify(reviewMapperService, never()).toEntity(requestDTO);
        verify(reviewMapperService, never()).toResponseDTO(any(Review.class));
    }

    @Test
    void  deleteReview_WithValidData_ReturnsNoContent() {
        String id = "1";
        Review existingReview = new Review(id, "customerId", "restaurantId", 5, "Great", LocalDateTime.now());

        when(reviewRepository.findById(id)).thenReturn(Optional.of(existingReview));

        reviewService.deleteById(id);

        verify(reviewRepository).findById(id);
        verify(reviewRepository).delete(existingReview);
    }

    @Test
    void deleteReview_WithInvalidData_ReturnsNotFound() {
        String id = "1";

        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reviewService.deleteById(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(reviewRepository).findById(id);
        verify(reviewRepository, never()).delete(any(Review.class));
    }
}
