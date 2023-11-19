package com.example.tripplan.models;

import com.example.tripplan.models.utils.Gender;
import com.example.tripplan.models.utils.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private Gender gender;
    private String profilePicture;
//    @Indexed(unique = true)
    private String emailAddress;
    private Location location;
//    private String phoneNumber;
    private String password;
}
