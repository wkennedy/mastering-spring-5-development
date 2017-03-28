package com.github.wkennedy.controllers;

import com.github.wkennedy.dto.Person;
import com.github.wkennedy.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SimpleController {

    private final SimpleService simpleService;

    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("/persons")
    public Flux<Person> getPersons() {
        return simpleService.getPersons();
    }
}
