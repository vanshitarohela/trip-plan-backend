package com.example.tripplan.services.interfaces;

import com.example.tripplan.models.Hotel;
import com.example.tripplan.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface HotelInterface {
    public List<Hotel> getAllHotels();
    public Optional<Hotel> getHotelById(String id);
    public Optional<List<Hotel>> getHotelsByCity(String city);
//    public Optional<List<Restaurant>> getRestaurantsByPrice(double price);
}
