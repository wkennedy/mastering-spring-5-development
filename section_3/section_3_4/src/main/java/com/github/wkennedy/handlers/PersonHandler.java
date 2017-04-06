package com.github.wkennedy.handlers;

import com.github.wkennedy.dto.Person;
import com.github.wkennedy.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    private final SimpleService simpleService;

    @Autowired
    public PersonHandler(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        Mono<Person> persons = simpleService.getPerson();
        return ServerResponse.ok().body(persons, Person.class);
    }

    public Mono<ServerResponse> getPersons(ServerRequest request) {
        Flux<Person> persons = simpleService.getPersons().log();;
        return ServerResponse.ok().body(persons, Person.class);
    }
}
