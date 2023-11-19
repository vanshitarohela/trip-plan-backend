package com.example.tripplan.controllers;

import com.example.tripplan.models.Flight;
import com.example.tripplan.services.FlightService;
import org.apache.catalina.filters.ExpiresFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {
    private final static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<List<Flight>>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getFlightById(@PathVariable String id) {
        Optional<Flight> res = flightService.getFlightById(id);
        if (res.isPresent()) {
            return new ResponseEntity<Optional<Flight>>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/travel")
    public ResponseEntity getFlightFromCityAndToCity(@RequestParam String departureCity, String arrivalCity) {
        Optional<List<Flight>> res = flightService.getFlightFromAndToCity(departureCity, arrivalCity);
        return res.map(flights -> new ResponseEntity<>(res, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}