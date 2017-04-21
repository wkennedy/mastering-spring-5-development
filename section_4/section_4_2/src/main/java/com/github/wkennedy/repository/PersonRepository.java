package com.github.wkennedy.repository;

import com.github.wkennedy.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InlineAddress.class)
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

}
