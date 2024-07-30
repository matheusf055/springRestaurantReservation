package com.restaurantreservation.dto.mapper;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantMapperService {

    private final ModelMapper modelMapper;

    public Restaurant toEntity(RestaurantRequestDTO requestDTO){
        return modelMapper.map(requestDTO, Restaurant.class);
    }

    public RestaurantResponseDTO toResponseDTO(Restaurant restaurant){
        return modelMapper.map(restaurant, RestaurantResponseDTO.class);
    }
}
