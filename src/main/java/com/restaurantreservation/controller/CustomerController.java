package com.restaurantreservation.controller;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable String id){
        return customerService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable String id, @RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = customerService.update(id, customerRequestDTO);
        return ResponseEntity.ok(customerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable String id){
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
