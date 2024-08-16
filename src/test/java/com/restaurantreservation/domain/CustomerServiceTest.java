package com.restaurantreservation.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.restaurantreservation.dto.CustomerRequestDTO;
import com.restaurantreservation.dto.CustomerResponseDTO;
import com.restaurantreservation.dto.mapper.CustomerMapperService;
import com.restaurantreservation.entity.Customer;
import com.restaurantreservation.repository.CustomerRepository;
import com.restaurantreservation.services.CustomerService;
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

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapperService customerMapperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_WithValidData_ReturnsResponseDTO() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("Matheus", "matheus@gmail.com", "1234567890");
        Customer customer = new Customer(null, "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO("1", "Matheus", "matheus@gmail.com", "1234567890", LocalDateTime.now());

        when(customerMapperService.toEntity(requestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapperService.toResponseDTO(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.register(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(customerMapperService).toEntity(requestDTO);
        verify(customerRepository).save(customer);
        verify(customerMapperService).toResponseDTO(customer);
    }

    @Test
    void findAll_ReturnsListOfCustomerResponseDto() {
        Customer customer = new Customer("1", "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO("1", "Matheus", "matheus@gmail.com", "1234567890", LocalDateTime.now());

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapperService.toResponseDTO(customer)).thenReturn(responseDTO);

        List<CustomerResponseDTO> result = customerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
        verify(customerRepository).findAll();
        verify(customerMapperService).toResponseDTO(customer);
    }

    @Test
    void findById_WithExistingId_ReturnsResponseDTO() {
        String id = "1";
        Customer customer = new Customer(id, "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "Matheus", "matheus@gmail.com", "1234567890", LocalDateTime.now());

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapperService.toResponseDTO(customer)).thenReturn(responseDTO);

        Optional<CustomerResponseDTO> result = customerService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(responseDTO, result.get());
        verify(customerRepository).findById(id);
        verify(customerMapperService).toResponseDTO(customer);
    }

    @Test
    void findById_WithUnexistingId_ReturnsNotFound() {
        String id = "1";

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CustomerResponseDTO> result = customerService.findById(id);

        assertFalse(result.isPresent());
        verify(customerRepository).findById(id);
        verify(customerMapperService, never()).toResponseDTO(any(Customer.class));
    }

    @Test
    void updateCustomer_WithValidData_ReturnsResponseDTO() {
        String id = "1";
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("Matheus", "matheus@gmail.com", "1234567890");
        Customer existingCustomer = new Customer(id, "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());
        Customer updatedCustomer = new Customer(id, "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "Matheus", "matheus@gmail.com", "1234567890", LocalDateTime.now());

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(customerMapperService.toEntity(requestDTO)).thenReturn(updatedCustomer);
        when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);
        when(customerMapperService.toResponseDTO(updatedCustomer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.update(id, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(customerRepository).findById(id);
        verify(customerMapperService).toEntity(requestDTO);
        verify(customerRepository).save(updatedCustomer);
        verify(customerMapperService).toResponseDTO(updatedCustomer);
    }

    @Test
    void updateCustomer_WithValidData_ReturnsNotFound() {
        String id = "1";
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("Matheus", "matheus@gmail.com", "1234567890");

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.update(id, requestDTO);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(customerRepository).findById(id);
        verify(customerRepository, never()).save(any(Customer.class));
        verify(customerMapperService, never()).toEntity(requestDTO);
        verify(customerMapperService, never()).toResponseDTO(any(Customer.class));
    }

    @Test
    void deleteCustomer_WithValidData_ReturnsNoContent() {
        String id = "1";
        Customer existingCustomer = new Customer(id, "matheus@gmail.com", "Matheus", "1234567890", LocalDateTime.now());

        when(customerRepository.findById(id)).thenReturn(Optional.of(existingCustomer));

        customerService.deleteById(id);

        verify(customerRepository).findById(id);
        verify(customerRepository).delete(existingCustomer);
    }

    @Test
    void deleteCustomer_WithInvalidData_ReturnsNotFound() {
        String id = "1";

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            customerService.deleteById(id);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(customerRepository).findById(id);
        verify(customerRepository, never()).delete(any(Customer.class));
    }
}
