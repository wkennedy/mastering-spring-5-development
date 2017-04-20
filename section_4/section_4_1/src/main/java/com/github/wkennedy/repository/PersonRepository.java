package com.github.wkennedy.repository;

import com.github.wkennedy.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

}
