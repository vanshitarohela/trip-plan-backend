package com.example.tripplan.services;

import com.example.tripplan.models.User;
import com.example.tripplan.repositories.UserRepository;
import com.example.tripplan.services.interfaces.UserInterface;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserInterface {
    @Autowired
    public UserRepository userRepository;

    @Override
    // Gets all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    // Gets a user by passing id
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    // Gets user based on city
    public Optional<List<User>> getUserByCity(String city) {
        return userRepository.getUserByCity(city);
    }

    @Override
    // Updates existing user and throws error if user is not found
    public User updateUser(User user) {
        Optional<User> currUser = userRepository.findById(user.getId());
        if (currUser.isPresent()){
            User u1 = currUser.get();
            u1.setLocation(user.getLocation());
            u1.setGender(user.getGender());
//            u1.setPhoneNumber(user.getPhoneNumber());
            u1.setName(user.getName());
            u1.setEmailAddress(user.getEmailAddress());
            u1.setProfilePicture(user.getProfilePicture());
            return u1;
        }
        else {
            throw new IllegalStateException("User not found");
        }
    }

    @Override
    // For saving user and gives error when user already exists
    public User insertUser(User user) {
        Optional<User> loginUser =  userRepository.findByEmailAddressAndPassword(user.getEmailAddress(), user.getPassword());
        if (!loginUser.isPresent()) {
            User registeredUser = userRepository.save(user);
            return registeredUser;
        }
        else {
            throw new IllegalStateException("User already exists");
        }
    }

    @Override
    @Transactional
    // Deletes a user
    public User deleteUser(User user) {
        Optional<User> currUser = userRepository.findById(user.getId());
        if (currUser.isPresent()) {
            User u1 = currUser.get();
            userRepository.delete(u1);
            return u1;
        }
        else {
            throw new IllegalStateException("User not found");
        }
    }

    // Verifies the login details
    // Throws IllegalArgumentException when email and password don't match
    // Throws IllegalStateException when user doesn't exist
    public User verifyAuthentication(String email, String password) {
        Optional<User> loginUser =  userRepository.findByEmailAddress(email);
        if (loginUser.isPresent()) {
            User user = loginUser.get();
            if(user.getPassword().equals(password)) {
                return user;
            }
            else {
                throw new IllegalArgumentException("Email and password don't match");
            }
        }
        else {
            throw new IllegalStateException("User doesn't exist. Please register!");
        }
    }
}
