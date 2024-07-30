package com.restaurantreservation.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "tb_customer")
@AllArgsConstructor @NoArgsConstructor @Data
public class Customer {

    @MongoId
    private String id;

    @Indexed(unique = true)
    private String email;

    private String name;

    private String phoneNumber;
}
