package com.example.tripplan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String id;
    private LocalDateTime departureTimestamp;
    private LocalDateTime arrivalTimestamp;
    private String departureCity;
    private String arrivalCity;
    private double price;
    private String name;
}
