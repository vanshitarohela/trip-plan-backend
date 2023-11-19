package com.example.tripplan.controllers;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.example.tripplan.models.Hotel;
import com.example.tripplan.models.User;
import com.example.tripplan.services.HotelService;
import com.example.tripplan.services.interfaces.HotelInterface;
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
@RequestMapping("/api/v1/hotels")
@CrossOrigin(origins = "http://localhost:4200/")
public class HotelController{
    private final static Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return new ResponseEntity<List<Hotel>>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getHotelById(@PathVariable String id) {
        System.out.println(id);
        Optional<Hotel> res = hotelService.getHotelById(id);
        if (res.isPresent()) {
            return new ResponseEntity<Optional<Hotel>>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Hotel> insertHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<Hotel>(hotelService.insertHotel(hotel), HttpStatus.CREATED);
    }

    //Used RequestParam instead of using something in getMapping
    @GetMapping("/city")
    public ResponseEntity getHotelsByCity(@RequestParam String city) {
        Optional<List<Hotel>> res = hotelService.getHotelsByCity(city);
        return res.map(hotels -> new ResponseEntity<>(hotels, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity updateHotel(Hotel hotel) {
        try {
            return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            // logs detailed error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Hotel> deleteHotel(Hotel hotel) {
        try {
            return new ResponseEntity<Hotel>(hotelService.deleteHotel(hotel), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}