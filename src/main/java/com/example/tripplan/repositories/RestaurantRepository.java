package com.example.tripplan.repositories;

import com.example.tripplan.models.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    @Query("{'location.city' : ?0}")
    public Optional<List<Restaurant>> findRestaurantByCity(String city);
}
