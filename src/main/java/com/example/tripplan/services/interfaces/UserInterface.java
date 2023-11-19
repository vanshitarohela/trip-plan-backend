package com.example.tripplan.services.interfaces;

import com.example.tripplan.models.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id);
    public Optional<List<User>> getUserByCity(String city);
    public User updateUser(User user);
    public User insertUser(User user);
    public User deleteUser(User user);
}
