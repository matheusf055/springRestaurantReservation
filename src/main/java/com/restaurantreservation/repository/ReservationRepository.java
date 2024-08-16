package com.restaurantreservation.repository;

import com.restaurantreservation.dto.ReservationResponseDTO;
import com.restaurantreservation.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<ReservationResponseDTO> findByCustomerId(String customerId);

    List<ReservationResponseDTO> findByRestaurantId(String restaurantId);
}
