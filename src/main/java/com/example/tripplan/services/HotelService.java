package com.example.tripplan.services;

import com.example.tripplan.models.Hotel;
import com.example.tripplan.repositories.HotelRepository;
import com.example.tripplan.repositories.HotelRepository;
import com.example.tripplan.services.interfaces.HotelInterface;
import com.example.tripplan.services.interfaces.HotelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements HotelInterface {
    @Autowired
    public HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(String id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Optional<List<Hotel>> getHotelsByCity(String city) {
         return hotelRepository.findHotelsByCity(city);
    }

//    @Override
//    public Optional<List<Hotel>> getHotelsByPrice(double price) {
//        return this.hotelRepository.;
//    }
//    TODO: implement price filter

    public Hotel insertHotel(Hotel hotel){
        return this.hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel updateHotel(Hotel hotel) {
        Optional<Hotel> currHotel = hotelRepository.findById(hotel.getId());
        if (currHotel.isPresent()) {
            Hotel r1 = currHotel.get();
            r1.setName(hotel.getName());
            r1.setEmail(hotel.getEmail());
            r1.setLocation(hotel.getLocation());
            r1.setDescription(hotel.getDescription());
            r1.setPhoneNumber(hotel.getPhoneNumber());
            r1.setPrice(hotel.getPrice());
            return r1;
        }
        else {
            throw new IllegalStateException("Hotel not found!");
        }
    }

    @Transactional
    public Hotel deleteHotel(Hotel hotel) {
        Optional<Hotel> currHotel = hotelRepository.findById(hotel.getId());
        if (currHotel.isPresent()) {
            Hotel r1 = currHotel.get();
            hotelRepository.delete(r1);
            return r1;
        }
        else {
            throw new IllegalStateException("User not found");
        }
    }
}
