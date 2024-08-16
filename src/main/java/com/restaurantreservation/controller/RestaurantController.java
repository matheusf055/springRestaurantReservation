package com.restaurantreservation.controller;

import com.restaurantreservation.dto.RestaurantRequestDTO;
import com.restaurantreservation.dto.RestaurantResponseDTO;
import com.restaurantreservation.services.RestaurantService;
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
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Tag(name = "Restaurants", description = "Endpoints for Restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "Creates a restaurant", description = "Creates a restaurant", tags = {"Restaurants"}, responses = {
            @ApiResponse(description = "Created", responseCode = "200", content = @Content(schema = @Schema(implementation = RestaurantResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<RestaurantResponseDTO> create(@RequestBody RestaurantRequestDTO restaurantRequestDTO){
        RestaurantResponseDTO restaurantResponseDTO = restaurantService.create(restaurantRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Find all restaurants", description = "Find all restaurants", tags = {"Restaurants"}, responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDTO.class))
            )),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<RestaurantResponseDTO>> findAll(){
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a restaurants by id", description = "Finds a restaurants by id", tags = {"Restaurants"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDTO.class)))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<RestaurantResponseDTO> findById(@PathVariable String id){
        return restaurantService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a restaurant", description = "Updates a restaurant", tags = {"Restaurants"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = RestaurantResponseDTO.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable String id, @RequestBody RestaurantRequestDTO restaurantRequestDTO){
        RestaurantResponseDTO restaurantResponseDTO = restaurantService.update(id ,restaurantRequestDTO);
        return ResponseEntity.ok(restaurantResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a restaurant", description = "Deletes a restaurant", tags = {"Restaurants"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
