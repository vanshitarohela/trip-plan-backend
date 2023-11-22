package com.example.tripplan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    private String id;
    private String dealType;
    private String imageAddress;
    private String packageName;
    private int days;
    private int numberOfPlaces;
    private int totalCost;
}
