package com.restaurantreservation.controller;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);
        return ResponseEntity.ok(customerResponseDTO);
    }
}
