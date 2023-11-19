package com.example.tripplan.controllers;

import com.example.tripplan.models.Restaurant;
import com.example.tripplan.models.User;
import com.example.tripplan.services.RestaurantService;
import com.example.tripplan.services.UserService;
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
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = {"http://localhost:4200/", "https://trip-plan-frontend.netlify.app/"})
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        Optional<User> res = userService.getUserById(id);
        if (res.isPresent()) {
            return new ResponseEntity<Optional<User>>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        try {
            return new ResponseEntity<User>(userService.insertUser(user), HttpStatus.CREATED);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT); // status code 409
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<User> verifyAuthentication(@RequestBody String email, String password) {
        try {
            return new ResponseEntity<User>(userService.verifyAuthentication(email, password), HttpStatus.OK);
        }
        catch (IllegalStateException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Used RequestParam instead of using something in getMapping
    @GetMapping("/city")
    public ResponseEntity getUsersByCity(@RequestParam String city) {
        Optional<List<User>> res = userService.getUserByCity(city);
        return res.map(users -> new ResponseEntity<>(users, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(User user) {
        try {
            return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            // logs detailed error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(User user) {
        try {
            return new ResponseEntity<User>(userService.deleteUser(user), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
