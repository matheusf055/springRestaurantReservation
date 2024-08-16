package com.restaurantreservation.dto.mapper;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerMapperService {

    private final ModelMapper modelMapper;

    public Customer toEntity(CustomerRequestDTO customerRequestDTO){
        return modelMapper.map(customerRequestDTO, Customer.class);
    }

    public CustomerResponseDTO toResponseDTO(Customer customer){
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }
}
