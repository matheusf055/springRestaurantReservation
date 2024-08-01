package com.restaurantreservation.controller;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> create(@RequestBody RestaurantRequestDTO restaurantRequestDTO){
        RestaurantResponseDTO restaurantResponseDTO = restaurantService.create(restaurantRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> findAll(){
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable String id){
        return restaurantService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable String id, @RequestBody RestaurantRequestDTO restaurantRequestDTO){
        RestaurantResponseDTO restaurantResponseDTO = restaurantService.update(id ,restaurantRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
