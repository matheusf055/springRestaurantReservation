package com.restaurantreservation.controller;

import com.restaurantreservation.dto.ReservationRequestDTO;
import com.restaurantreservation.dto.ReservationResponseDTO;
import com.restaurantreservation.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO restaurantResponseDTO = reservationService.create(reservationRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationResponseDTO>> findReservationByCustomerId(@PathVariable String customerId){
        List<ReservationResponseDTO> reservation = reservationService.findReservationByCustomerId(customerId);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReservationResponseDTO>> findReservationByRestaurantId(@PathVariable String restaurantId){
        List<ReservationResponseDTO> reservation = reservationService.findReservationByRestaurantId(restaurantId);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable String id, @RequestBody ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO reservationResponseDTO = reservationService.update(id, reservationRequestDTO);
        return ResponseEntity.ok(reservationResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
