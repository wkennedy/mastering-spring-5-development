package com.github.wkennedy.services;

import com.github.wkennedy.entities.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleServiceTest {

    @Autowired
    private SimpleService simpleService;

    @Test
    public void createPerson() throws Exception {
        Person person = new Person("Doe", "John");
        Mono<Person> personMono = simpleService.createPerson(person);
        personMono.then().block();
        Mono<Person> personReturned = simpleService.getPerson(person.getId());
//        personReturned.subscribe(System.out::println);
        System.out.println(personReturned.block().toString());
    }

    @Test
    public void createPersons() throws Exception {
    }

    @Test
    public void getPerson() throws Exception {
    }

    @Test
    public void getPersons() throws Exception {
        simpleService.deleteAll();
        Flux<Person> personFlux = simpleService.getPersons();
        personFlux.then().block();
        System.out.println(personFlux.blockLast().toString());
    }

    @Test
    public void getPersonsWithDelay() throws Exception {
    }

}