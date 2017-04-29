package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface PersonReactiveCassandraRepository extends ReactiveCassandraRepository<Person, String>{
}
