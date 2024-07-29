package com.restaurantreservation.repository;

import com.restaurantreservation.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}
