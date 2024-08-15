package com.restaurantreservation.services;

import com.restaurantreservation.dto.*;
import com.restaurantreservation.dto.mapper.ReservationMapperService;
import com.restaurantreservation.entity.Reservation;
import com.restaurantreservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapperService reservationMapperService;

    public ReservationResponseDTO create(ReservationRequestDTO reservationRequestDTO){
        Reservation reservation = reservationMapperService.toEntity(reservationRequestDTO);
        Reservation createdReservation = reservationRepository.save(reservation);
        return reservationMapperService.toResponseDTO(createdReservation);
    }

    public List<ReservationResponseDTO> findAll(){
        return reservationRepository.findAll().stream().map(reservationMapperService::toResponseDTO).toList();
    }

    public List<ReservationResponseDTO> findReservationByCustomerId(String customerId){
        return reservationRepository.findByCustomerId(customerId);
    }

    public List<ReservationResponseDTO> findReservationByRestaurantId(String restaurantId){
        return reservationRepository.findByRestaurantId(restaurantId);
    }

    public ReservationResponseDTO update(String id, ReservationRequestDTO reservationRequestDTO){
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        Reservation updatedReservation = reservationMapperService.toEntity(reservationRequestDTO);
        updatedReservation.setId(existingReservation.getId());

        Reservation savedReservation = reservationRepository.save(updatedReservation);
        return reservationMapperService.toResponseDTO(savedReservation);
    }

    public void deleteById(String id){
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        reservationRepository.delete(existingReservation);
    }
}
