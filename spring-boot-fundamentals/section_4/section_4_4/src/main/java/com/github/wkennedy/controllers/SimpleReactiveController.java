package com.github.wkennedy.controllers;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonReactiveMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/react")
public class SimpleReactiveController {

    private static final Logger log = LoggerFactory.getLogger(SimpleReactiveController.class);

    private final PersonReactiveMongoRepository personReactiveMongoRepository;

    @Autowired
    public SimpleReactiveController(PersonReactiveMongoRepository personReactiveMongoRepository) {
        this.personReactiveMongoRepository = personReactiveMongoRepository;
    }

    @GetMapping("/persons")
    public Flux<Person> getPersons() {
        return personReactiveMongoRepository.findAll()
                .log()
                .doOnComplete(() -> log.info("Endpoint /persons complete."));
    }

    @GetMapping("/persons/delay/{delay}")
    public Flux<Person> getPersons(@PathVariable Long delay) {
        return personReactiveMongoRepository.findAll()
                .delayElements(Duration.ofMillis(delay))
                .log()
                .doOnComplete(() -> log.info("Endpoint /persons/delay/" + delay + " complete."));
    }

    @GetMapping("/person/{id}")
    public Mono<Person> getPerson(@PathVariable String id) {
        return personReactiveMongoRepository.findById(id);
    }
}
