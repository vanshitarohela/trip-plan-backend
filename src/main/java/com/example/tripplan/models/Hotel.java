package com.example.tripplan.models;

import com.example.tripplan.models.utils.Location;
import com.example.tripplan.models.utils.Review;
import com.example.tripplan.models.utils.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.sound.sampled.FloatControl;
import java.util.List;

@Document(collection = "hotels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;
    private List<Review> reviews;
    private double price;
    private String description;
    private List<String> images;
//    OCCUPANCY
//    AVAILABILITY?
//    TIMINGS?
}
