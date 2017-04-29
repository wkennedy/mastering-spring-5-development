package com.github.wkennedy.services;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonReactiveMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Service
public class SimpleService {

    private static final Logger log = LoggerFactory.getLogger(SimpleService.class);

    private final PersonReactiveMongoRepository personReactiveMongoRepository;

    @Autowired
    public SimpleService(PersonReactiveMongoRepository personReactiveMongoRepository) {
        this.personReactiveMongoRepository = personReactiveMongoRepository;
    }

    public Mono<Person> createPerson(Person person) {
        return personReactiveMongoRepository.save(person);
    }

    public Flux<Person> createPersons() {
        Stream<Person> personStream = IntStream.range(0, 10_000)
                .parallel()
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));

        return personReactiveMongoRepository.save(Flux.fromStream(personStream));
    }

    public Mono<Person> getPerson(String id) {
        return personReactiveMongoRepository.findOne(id);
    }

    public Flux<Person> getPersons() {
        return personReactiveMongoRepository.findAll();
    }

    public Flux<Person> getPersonsWithDelay(Long millis) {
        Stream<Person> personStream = IntStream.range(0, 10_000)
                .parallel()
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));
        return Flux.fromStream(personStream).delayElements(Duration.ofMillis(millis));
    }

    public void deleteAll() {
        personReactiveMongoRepository.deleteAll();
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
