package com.restaurantreservation.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "tb_customer")
@AllArgsConstructor @NoArgsConstructor @Data
public class Customer {

    @MongoId
    private String id;

    @Indexed(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;
}
