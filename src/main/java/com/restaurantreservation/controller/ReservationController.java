package com.restaurantreservation.controller;

import com.restaurantreservation.dto.ReservationRequestDTO;
import com.restaurantreservation.dto.ReservationResponseDTO;
import com.restaurantreservation.services.ReservationService;
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
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Tag(name = "Reservations", description = "Endpoints for Reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "Creates a reservation", description = "Creates a reservation", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservationResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO restaurantResponseDTO = reservationService.create(reservationRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Find all reservations", description = "Find all reservations", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReservationResponseDTO.class))
            )),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReservationResponseDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Finds a reservation by customer id", description = "Finds a reservation by customer id", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReservationResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReservationResponseDTO>> findReservationByCustomerId(@PathVariable String customerId){
        List<ReservationResponseDTO> reservation = reservationService.findReservationByCustomerId(customerId);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Finds a reservation by restaurant id", description = "Finds a reservation by restaurant id", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ReservationResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ReservationResponseDTO>> findReservationByRestaurantId(@PathVariable String restaurantId){
        List<ReservationResponseDTO> reservation = reservationService.findReservationByRestaurantId(restaurantId);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a reservation", description = "Updates a reservation", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = ReservationResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable String id, @RequestBody ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO reservationResponseDTO = reservationService.update(id, reservationRequestDTO);
        return ResponseEntity.ok(reservationResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a reservation", description = "Deletes a reservation", tags = {"Reservations"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable String id){
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
