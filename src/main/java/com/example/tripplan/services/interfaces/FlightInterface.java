package com.example.tripplan.services.interfaces;

import com.example.tripplan.models.Flight;
import org.bson.types.ObjectId;


import java.util.List;
import java.util.Optional;

public interface FlightInterface {
    public List<Flight> getAllFlights();

    Optional<Flight> getFlightById(String id);
    public Optional<List<Flight>> getFlightFromAndToCity(String fromCity, String toCity);
}
