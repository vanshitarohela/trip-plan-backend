package com.example.tripplan.repositories;

import com.example.tripplan.models.Package;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackageRepository extends MongoRepository<Package, String> {
}
