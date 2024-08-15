package com.restaurantreservation.dto.mapper;

import com.restaurantreservation.dto.ReservationRequestDTO;
import com.restaurantreservation.dto.ReservationResponseDTO;
import com.restaurantreservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationMapperService {

    private final ModelMapper modelMapper;

    public Reservation toEntity(ReservationRequestDTO reservationRequestDTO){
       Reservation reservation = new Reservation();
       reservation.setCustomerId(reservationRequestDTO.getCustomerId());
       reservation.setRestaurantId(reservationRequestDTO.getRestaurantId());
       reservation.setNumberOfPeople(reservationRequestDTO.getNumberOfPeople());
       return reservation;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation){
        return modelMapper.map(reservation, ReservationResponseDTO.class);
    }
}
