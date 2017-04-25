package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PersonRepository extends CassandraRepository<Person> {
}
