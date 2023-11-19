package com.example.tripplan.services;

import com.example.tripplan.models.Flight;
import com.example.tripplan.repositories.FlightRepository;
import com.example.tripplan.services.interfaces.FlightInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements FlightInterface {
    @Autowired
    public FlightRepository flightRepository;

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> getFlightById(String id) {
        return flightRepository.findById(id);
    }

    @Override
    public Optional<List<Flight>> getFlightFromAndToCity(String fromCity, String toCity) {
        return flightRepository.getFlightFromCityAndToCity(fromCity, toCity);
    }
}
