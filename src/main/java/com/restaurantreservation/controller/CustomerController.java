package com.restaurantreservation.controller;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Endpoints for customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Register a customer", description = "Register a customer", tags = {"Customers"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<CustomerResponseDTO> register(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);
        return ResponseEntity.ok(customerResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Find all customers", description = "Find all customers", tags = {"Customers"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDTO.class))
            )),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<CustomerResponseDTO>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a customer by id", description = "Finds a customer by id", tags = {"Customers"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable String id){
        return customerService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a customer", description = "Updates a customer", tags = {"Customers"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable String id, @RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = customerService.update(id, customerRequestDTO);
        return ResponseEntity.ok(customerResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a customer", description = "Deletes a customer", tags = {"Customers"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Void> deleteByid(@PathVariable String id){
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
