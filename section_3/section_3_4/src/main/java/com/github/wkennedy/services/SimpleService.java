package com.github.wkennedy.services;

import com.github.wkennedy.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
        Stream<Person> personStream = IntStream.range(0, 10_000)
                .parallel()
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));
        return Flux.fromStream(personStream);
    }

    public Flux<Person> getPersonsWithDelay(Long millis) {
        Stream<Person> personStream = IntStream.range(0, 10_000)
                .parallel()
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));
        return Flux.fromStream(personStream).delayElements(Duration.ofMillis(millis));
    }

//    public Flux<Person> getPersons() {
//        List<Person> personList = new ArrayList<>();
//        for(int i = 0; i < 100_000_000; i++) {
//            personList.add(new Person("John", "Doe" + i));
//        }
//
//        return Flux.fromIterable(personList);
//    }
}
