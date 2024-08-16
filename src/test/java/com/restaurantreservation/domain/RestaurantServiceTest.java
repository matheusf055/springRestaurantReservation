package com.restaurantreservation.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.entity.Restaurant;
import com.restaurantreservation.repository.RestaurantRepository;
import com.restaurantreservation.repository.ReviewRepository;
import com.restaurantreservation.dto.mapper.RestaurantMapperService;
import com.restaurantreservation.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantMapperService restaurantMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRestaurant_WithValidData_ReturnsResponseDTO() {
        RestaurantRequestDTO requestDTO = new RestaurantRequestDTO("name", "location", "kindOfFood");
        Restaurant restaurant = new Restaurant(null, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO("1", "name", "location", "kindOfFood", LocalDateTime.now(), List.of());

        when(restaurantMapperService.toEntity(requestDTO)).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        when(restaurantMapperService.toResponseDTO(restaurant)).thenReturn(responseDTO);

        RestaurantResponseDTO result = restaurantService.create(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(restaurantMapperService).toEntity(requestDTO);
        verify(restaurantRepository).save(restaurant);
        verify(restaurantMapperService).toResponseDTO(restaurant);
    }

    @Test
    void findAll_ReturnsListOfRestaurantsResponseDto() {
        Restaurant restaurant = new Restaurant("1", "name", "location", "kindOfFood", LocalDateTime.now(), List.of());
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO("1", "name", "location", "kindOfFood", LocalDateTime.now(), List.of());
        List<Restaurant> restaurants = List.of(restaurant);

        when(restaurantRepository.findAll()).thenReturn(restaurants);
        when(reviewRepository.findByRestaurantId(eq("1"))).thenReturn(List.of());
        when(restaurantMapperService.toResponseDTO(restaurant)).thenReturn(responseDTO);

        List<RestaurantResponseDTO> result = restaurantService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(restaurantRepository).findAll();
        verify(reviewRepository).findByRestaurantId(eq("1"));
        verify(restaurantMapperService).toResponseDTO(restaurant);
    }

    @Test
    void findById_WithExistingId_ReturnsResponseDTO() {
        String id = "1";
        Restaurant restaurant = new Restaurant(id, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(id, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));
        when(reviewRepository.findByRestaurantId(id)).thenReturn(List.of());
        when(restaurantMapperService.toResponseDTO(restaurant)).thenReturn(responseDTO);

        Optional<RestaurantResponseDTO> result = restaurantService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(responseDTO, result.get());
        verify(restaurantRepository).findById(id);
        verify(reviewRepository).findByRestaurantId(id);
        verify(restaurantMapperService).toResponseDTO(restaurant);
    }

    @Test
    void updateRestaurant_WithValidData_ReturnsResponseDTO() {
        String id = "1";
        RestaurantRequestDTO requestDTO = new RestaurantRequestDTO("name", "location", "kindOfFood");
        Restaurant existingRestaurant = new Restaurant(id, "oldName", "oldLocation", "oldKindOfFood", LocalDateTime.now(), List.of());
        Restaurant updatedRestaurant = new Restaurant(id, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO(id, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(existingRestaurant));
        when(restaurantMapperService.toEntity(requestDTO)).thenReturn(updatedRestaurant);
        when(restaurantRepository.save(updatedRestaurant)).thenReturn(updatedRestaurant);
        when(restaurantMapperService.toResponseDTO(updatedRestaurant)).thenReturn(responseDTO);

        RestaurantResponseDTO result = restaurantService.update(id, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(restaurantRepository).findById(id);
        verify(restaurantMapperService).toEntity(requestDTO);
        verify(restaurantRepository).save(updatedRestaurant);
        verify(restaurantMapperService).toResponseDTO(updatedRestaurant);
    }

    @Test
    void updateRestaurant_WithValidData_ReturnsNotFound() {
        String id = "1";
        RestaurantRequestDTO requestDTO = new RestaurantRequestDTO("name", "location", "kindOfFood");

        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> restaurantService.update(id, requestDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(restaurantRepository).findById(id);
        verify(restaurantRepository, never()).save(any(Restaurant.class));
        verify(restaurantMapperService, never()).toEntity(requestDTO);
        verify(restaurantMapperService, never()).toResponseDTO(any(Restaurant.class));
    }

    @Test
    void deleteRestaurant_WithValidData_ReturnsNoContent() {
        String id = "1";
        Restaurant existingRestaurant = new Restaurant(id, "name", "location", "kindOfFood", LocalDateTime.now(), List.of());

        when(restaurantRepository.findById(id)).thenReturn(Optional.of(existingRestaurant));

        restaurantService.deleteById(id);

        verify(restaurantRepository).findById(id);
        verify(restaurantRepository).delete(existingRestaurant);
    }

    @Test
    void deleteRestaurant_WithInvalidData_ReturnsNotFound() {
        String id = "1";

        when(restaurantRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> restaurantService.deleteById(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(restaurantRepository).findById(id);
        verify(restaurantRepository, never()).delete(any(Restaurant.class));
    }
}
