package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonReactiveMongoRepository extends ReactiveMongoRepository<Person, String> {
}
