package com.example.tripplan.controllers;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.example.tripplan.models.Restaurant;
import com.example.tripplan.models.User;
import com.example.tripplan.services.RestaurantService;
import com.example.tripplan.services.interfaces.RestaurantInterface;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurants")
@CrossOrigin(origins = "http://localhost:4200/")
public class RestaurantController{
    private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<List<Restaurant>>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRestaurantById(@PathVariable String id) {
        System.out.println(id);
        Optional<Restaurant> res = restaurantService.getRestaurantById(id);
        if (res.isPresent()) {
            return new ResponseEntity<Optional<Restaurant>>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Restaurant> insertRestaurant(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<Restaurant>(restaurantService.insertRestaurant(restaurant), HttpStatus.CREATED);
    }

    //Used RequestParam instead of using something in getMapping
    @GetMapping("/city")
    public ResponseEntity getRestaurantsByCity(@RequestParam String city) {
        Optional<List<Restaurant>> res = restaurantService.getRestaurantsByCity(city);
        return res.map(restaurants -> new ResponseEntity<>(restaurants, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity updateRestaurant(Restaurant restaurant) {
        try {
            return new ResponseEntity<Restaurant>(restaurantService.updateRestaurant(restaurant), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            // logs detailed error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Restaurant> deleteRestaurant(Restaurant restaurant) {
        try {
            return new ResponseEntity<Restaurant>(restaurantService.deleteRestaurant(restaurant), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}