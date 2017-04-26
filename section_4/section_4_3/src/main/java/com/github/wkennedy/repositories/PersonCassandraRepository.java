package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonCassandraRepository extends CassandraRepository<Person> {
}
