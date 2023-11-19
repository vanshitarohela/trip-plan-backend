package com.example.tripplan.repositories;

import com.example.tripplan.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.ObjectInput;
import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'location.city' : ?0}")
    public Optional<List<User>> getUserByCity(String city);

    public Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);
    public Optional<User> findByEmailAddress(String email);
}
