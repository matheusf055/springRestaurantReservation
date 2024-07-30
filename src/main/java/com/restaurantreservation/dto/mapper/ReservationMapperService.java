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
        return modelMapper.map(reservationRequestDTO, Reservation.class);
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation){
        return modelMapper.map(reservation, ReservationResponseDTO.class);
    }
}
