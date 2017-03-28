package com.github.wkennedy.services;

import com.github.wkennedy.dto.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SimpleService {

    public Mono<Person> getPerson() {
        Person person = new Person();
        person.setLastName("Doe");
        person.setFirstName("John");
        return Mono.just(person);
    }

    public Flux<Person> getPersons() {
        Person person = new Person();
        person.setLastName("Doe");
        person.setFirstName("John");

        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Person person2 = new Person();
        person2.setLastName("Doe");
        person2.setFirstName("Jane");

        return Flux.just(person, person2);
    }
}
