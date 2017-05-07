package com.github.wkennedy.controllers;

import com.github.wkennedy.entities.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ResourceController {

    @GetMapping("/")
    public String index() {
        return "Hello!";
    }

    @GetMapping("/person")
    public Person getPerson() {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setFirstName("John");
        person.setLastName("Doe");

        return person;
    }
}
