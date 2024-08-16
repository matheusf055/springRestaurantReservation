package com.restaurantreservation.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.restaurantreservation.dto.ReservationRequestDTO;
import com.restaurantreservation.dto.ReservationResponseDTO;
import com.restaurantreservation.dto.ReservationResponseDTO.Status;
import com.restaurantreservation.entity.Reservation;
import com.restaurantreservation.repository.ReservationRepository;
import com.restaurantreservation.services.ReservationService;
import com.restaurantreservation.dto.mapper.ReservationMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapperService reservationMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_WithValidData_ReturnsResponseDTO() {
        ReservationRequestDTO requestDTO = new ReservationRequestDTO("customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.PENDING);
        Reservation reservation = new Reservation(null, "customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.PENDING);
        ReservationResponseDTO responseDTO = new ReservationResponseDTO("1", "customerId", "restaurantId", LocalDateTime.now(), 4, Status.PENDING);

        when(reservationMapperService.toEntity(requestDTO)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapperService.toResponseDTO(reservation)).thenReturn(responseDTO);

        ReservationResponseDTO result = reservationService.create(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(reservationMapperService).toEntity(requestDTO);
        verify(reservationRepository).save(reservation);
        verify(reservationMapperService).toResponseDTO(reservation);
    }

    @Test
    void findAll_ReturnsListOfReservationsResponseDto() {
        Reservation reservation = new Reservation("1", "customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.PENDING);
        ReservationResponseDTO responseDTO = new ReservationResponseDTO("1", "customerId", "restaurantId", LocalDateTime.now(), 4, Status.PENDING);

        when(reservationRepository.findAll()).thenReturn(List.of(reservation));
        when(reservationMapperService.toResponseDTO(reservation)).thenReturn(responseDTO);

        List<ReservationResponseDTO> result = reservationService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(reservationRepository).findAll();
        verify(reservationMapperService).toResponseDTO(reservation);
    }

    @Test
    void findByCustomerId_WithExistingId_ReturnsResponseDTO() {
        String customerId = "customerId";
        ReservationResponseDTO responseDTO = new ReservationResponseDTO("1", customerId, "restaurantId", LocalDateTime.now(), 4, Status.PENDING);

        when(reservationRepository.findByCustomerId(customerId)).thenReturn(List.of(responseDTO));

        List<ReservationResponseDTO> result = reservationService.findReservationByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(reservationRepository).findByCustomerId(customerId);
    }

    @Test
    void findByRestaurantId_WithExistingId_ReturnsResponseDTO() {
        String restaurantId = "restaurantId";
        ReservationResponseDTO responseDTO = new ReservationResponseDTO("1", "customerId", restaurantId, LocalDateTime.now(), 4, Status.PENDING);

        when(reservationRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(responseDTO));

        List<ReservationResponseDTO> result = reservationService.findReservationByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(reservationRepository).findByRestaurantId(restaurantId);
    }

    @Test
    void updateReservation_WithValidData_ReturnsResponseDTO() {
        String id = "1";
        ReservationRequestDTO requestDTO = new ReservationRequestDTO("customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.RESERVED);
        Reservation existingReservation = new Reservation(id, "customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.PENDING);
        Reservation updatedReservation = new Reservation(id, "customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.RESERVED);
        ReservationResponseDTO responseDTO = new ReservationResponseDTO(id, "customerId", "restaurantId", LocalDateTime.now(), 4, Status.RESERVED);

        when(reservationRepository.findById(id)).thenReturn(Optional.of(existingReservation));
        when(reservationMapperService.toEntity(requestDTO)).thenReturn(updatedReservation);
        when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);
        when(reservationMapperService.toResponseDTO(updatedReservation)).thenReturn(responseDTO);

        ReservationResponseDTO result = reservationService.update(id, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(reservationRepository).findById(id);
        verify(reservationMapperService).toEntity(requestDTO);
        verify(reservationRepository).save(updatedReservation);
        verify(reservationMapperService).toResponseDTO(updatedReservation);
    }

    @Test
    void updateReservation_WithValidData_ReturnsNotFound() {
        String id = "1";
        ReservationRequestDTO requestDTO = new ReservationRequestDTO("customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.RESERVED);

        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.update(id, requestDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(reservationRepository).findById(id);
        verify(reservationRepository, never()).save(any(Reservation.class));
        verify(reservationMapperService, never()).toEntity(requestDTO);
        verify(reservationMapperService, never()).toResponseDTO(any(Reservation.class));
    }

    @Test
    void deleteReservation_WithValidData_ReturnsNoContent() {
        String id = "1";
        Reservation existingReservation = new Reservation(id, "customerId", "restaurantId", LocalDateTime.now(), 4, Reservation.Status.PENDING);

        when(reservationRepository.findById(id)).thenReturn(Optional.of(existingReservation));

        reservationService.deleteById(id);

        verify(reservationRepository).findById(id);
        verify(reservationRepository).delete(existingReservation);
    }

    @Test
    void deleteReservation_WithInvalidData_ReturnsNotFound() {
        String id = "1";

        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> reservationService.deleteById(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(reservationRepository).findById(id);
        verify(reservationRepository, never()).delete(any(Reservation.class));
    }
}
