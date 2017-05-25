package com.github.wkennedy.controllers;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonReactiveMongoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
//@WebFluxTest
public class SimpleReactiveControllerTest {

    private static final Logger log = LoggerFactory.getLogger(SimpleReactiveControllerTest.class);

    private WebTestClient webTestClient;

    @Autowired
    private PersonReactiveMongoRepository personReactiveMongoRepository;

    private Person person = new Person("Doe", "John");


    @Before
    public void before() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:8080").build();
//        this.webTestClient = WebTestClient.bindToController(new SimpleReactiveController(simpleService)).build();
        personReactiveMongoRepository.save(person).block();
        createPersons().then().block();
    }

    @After
    public void tearDown() throws Exception {
        personReactiveMongoRepository.deleteAll().block();
    }

    @Test
    public void getReactPerson() throws Exception {
        FluxExchangeResult<Person> personFlux = webTestClient
                .get().uri("/react/person/" + person.getId())
                .accept(MediaType.APPLICATION_JSON) // text/event-stream or application/stream+json
                .exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .returnResult(Person.class);

        personFlux.getResponseBody().toStream().forEach(System.out::println);
    }

    @Test
    public void getReactPersons() throws Exception {

        FluxExchangeResult<Person> personFlux = webTestClient
                .get().uri("/react/persons")
                .accept(MediaType.APPLICATION_JSON) // text/event-stream or application/stream+json
                .exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .returnResult(Person.class);

        personFlux.getResponseBody()
                .doOnNext(System.out::println)
                .doOnComplete(() -> System.out.println("Finished getReactPersons test")).log()
                .subscribe();

//        personFlux.getResponseBody()
//                .toStream().forEach(System.out::println);
    }

    @Test
    public void getStreamingPersons() throws Exception {
        FluxExchangeResult<Person> personResult = webTestClient.get().uri("/react/persons/delay/300")
                .accept(MediaType.APPLICATION_STREAM_JSON) //application/stream+json
                .exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.parseMediaType("application/stream+json;charset=UTF-8"))
                .returnResult(Person.class);

//        personResult.getResponseBody().toStream()
//                .forEach(person -> System.out.println("WebTestClient: " + person.toString()));

        CountDownLatch latch = new CountDownLatch(1);
        personResult.getResponseBody()
                .doOnNext(person -> System.out.println("WebTestClient: " + person.toString()))
                .doOnComplete(latch::countDown)
                .doOnError(throwable -> latch.countDown())
                .log()
                .subscribe();
        latch.await();

        System.out.println("Ending getStreamingPersons test");
    }

    private Flux<Person> createPersons() {
        Stream<Person> personStream = IntStream.range(0, 20)
                .parallel()
                .mapToObj(i -> new Person("John", "Doe" + i))
                .peek(person -> log.info("Creating person: " + person.toString()));

        return personReactiveMongoRepository.insert(Flux.fromStream(personStream));
    }

}