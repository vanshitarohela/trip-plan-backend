package com.example.tripplan.services;

import com.example.tripplan.models.Restaurant;
import com.example.tripplan.models.User;
import com.example.tripplan.repositories.RestaurantRepository;
import com.example.tripplan.services.interfaces.RestaurantInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements RestaurantInterface {
    @Autowired
    public RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getRestaurantById(String id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Optional<List<Restaurant>> getRestaurantsByCity(String city) {
         return restaurantRepository.findRestaurantByCity(city);
    }

//    @Override
//    public Optional<List<Restaurant>> getRestaurantsByPrice(double price) {
//        return this.restaurantRepository.;
//    }
//    TODO: implement price filter

    public Restaurant insertRestaurant(Restaurant restaurant){
        return this.restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> currRestaurant = restaurantRepository.findById(restaurant.getId());
        if (currRestaurant.isPresent()) {
            Restaurant r1 = currRestaurant.get();
            r1.setName(restaurant.getName());
            r1.setEmail(restaurant.getEmail());
            r1.setLocation(restaurant.getLocation());
            r1.setDescription(restaurant.getDescription());
            r1.setPhoneNumber(restaurant.getPhoneNumber());
            r1.setPrice(restaurant.getPrice());
            return r1;
        }
        else {
            throw new IllegalStateException("Restaurant not found!");
        }
    }

    @Transactional
    public Restaurant deleteRestaurant(Restaurant restaurant) {
        Optional<Restaurant> currRestaurant = restaurantRepository.findById(restaurant.getId());
        if (currRestaurant.isPresent()) {
            Restaurant r1 = currRestaurant.get();
            restaurantRepository.delete(r1);
            return r1;
        }
        else {
            throw new IllegalStateException("User not found");
        }
    }
}
