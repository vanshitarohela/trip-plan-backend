package com.example.tripplan.repositories;

import com.example.tripplan.models.Flight;
import com.example.tripplan.models.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface FlightRepository extends MongoRepository<Flight, String>  {
//    @Query("{'location.city' : ?0}")
    @Query("{ $and: [ { 'departureCity' : ?0 }, { 'arrivalCity' : ?1 } ] }")
    public Optional<List<Flight>> getFlightFromCityAndToCity(String departureCity, String arrivalCity);
}
