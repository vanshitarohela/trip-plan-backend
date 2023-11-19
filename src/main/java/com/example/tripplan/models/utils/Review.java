package com.example.tripplan.models.utils;

import com.example.tripplan.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id;
    private String reviewBody;
//    private ObjectId placeId;
    private int rating;
//    private ObjectId userId;
    private User user;
}
