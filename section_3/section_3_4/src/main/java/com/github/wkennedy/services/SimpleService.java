package com.github.wkennedy.services;

import com.github.wkennedy.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class SimpleService {

    private static final Logger log = LoggerFactory.getLogger(SimpleService.class);

    public Mono<Person> getPerson() {
        Person person = new Person();
        person.setLastName("Doe");
        person.setFirstName("John");

        return Mono.just(person);
    }

    public Flux<Person> getPersons() {
        Stream<Person> personStream = IntStream.range(1, 10000)
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));
        return Flux.fromStream(personStream);
    }
}
