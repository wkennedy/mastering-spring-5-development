package com.github.wkennedy.service;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    private final PersonMongoRepository personMongoRepository;

    @Autowired
    public SimpleService(PersonMongoRepository personMongoRepository) {
        this.personMongoRepository = personMongoRepository;
    }

    @CacheEvict(value="findById", key = "#person?.id")
    public void save(Person person) {
        personMongoRepository.save(person);
    }

    @Cacheable(value="findById", key="#id")
    public Person getPersonById(String id) {
        artificialLatency(3000L);
        return personMongoRepository.findById(id).get();
    }

    private void artificialLatency(long milliseconds){
        try {
            System.out.println("Artificial latency...");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
