package com.github.wkennedy.repository;

import com.github.wkennedy.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "address", path = "address")
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}