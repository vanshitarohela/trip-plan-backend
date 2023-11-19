package com.example.tripplan.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String pincode;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String link;
}