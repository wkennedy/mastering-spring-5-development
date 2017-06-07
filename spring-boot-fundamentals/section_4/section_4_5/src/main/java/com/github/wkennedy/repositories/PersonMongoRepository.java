package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonMongoRepository extends MongoRepository<Person, String> {

    @Override
    @CacheEvict(value = "findById", key = "#root.args[0].id")
    <S extends Person> S save(S entity);

    @Cacheable(value = "findById", key = "#a0")
    Optional<Person> findById(String id);

}
