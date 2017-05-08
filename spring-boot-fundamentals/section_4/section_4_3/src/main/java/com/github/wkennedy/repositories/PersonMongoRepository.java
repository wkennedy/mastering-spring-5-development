package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonMongoRepository extends MongoRepository<Person, UUID> {
}
