package com.github.wkennedy.controllers;//package com.github.wkennedy.controllers;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/react")
public class SimpleReactiveController {

    private final SimpleService simpleService;

    @Autowired
    public SimpleReactiveController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("/persons")
    public Flux<Person> getPersons() {
        return simpleService.getPersons().log();
    }

    @GetMapping("/persons/delay/{delay}")
    public Flux<Person> getPersons(@PathVariable Long delay) {
        return simpleService.getPersons().delayElements(Duration.ofMillis(delay));
    }

    @GetMapping("/person/{id}")
    public Mono<Person> getPerson(@PathVariable String id) {
        return simpleService.getPerson(id);
    }
}
