package com.restaurantreservation.services;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.dto.mapper.CustomerMapperService;
import com.restaurantreservation.entity.Customer;
import com.restaurantreservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapperService customerMapperService;

    public CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO){
        Customer customer = customerMapperService.toEntity(customerRequestDTO);
        Customer createdCustomer = customerRepository.save(customer);
        return customerMapperService.toResponseDTO(createdCustomer);
    }

    public Optional<CustomerResponseDTO> findById(String id) {
        return customerRepository.findById(id).map(customerMapperService::toResponseDTO);
    }

    public CustomerResponseDTO update(String id, CustomerRequestDTO customerRequestDTO){
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Customer updatedCustomer = customerMapperService.toEntity(customerRequestDTO);
        updatedCustomer.setId(existingCustomer.getId());

        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapperService.toResponseDTO(savedCustomer);
    }

    public void deleteById(String id){
        customerRepository.deleteById(id);
    }
}
