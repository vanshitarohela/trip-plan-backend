package com.example.tripplan.services.interfaces;

import com.example.tripplan.models.Restaurant;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface RestaurantInterface {
    public List<Restaurant> getAllRestaurants();
    public Optional<Restaurant> getRestaurantById(String id);
    public Optional<List<Restaurant>> getRestaurantsByCity(String city);
//    public Optional<List<Restaurant>> getRestaurantsByPrice(double price);
}
