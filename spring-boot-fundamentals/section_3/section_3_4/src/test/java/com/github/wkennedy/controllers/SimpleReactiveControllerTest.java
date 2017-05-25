package com.github.wkennedy.controllers;

import com.github.wkennedy.dto.Person;
import com.github.wkennedy.services.SimpleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.net.URISyntaxException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
//@WebFluxTest
public class SimpleReactiveControllerTest {

    private WebTestClient webTestClient;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void before() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:8080").build();
//        this.webTestClient = WebTestClient.bindToController(new SimpleReactiveController(simpleService)).build();
    }

    @Ignore
    @Test
    public void getReactPersons() throws Exception {
        FluxExchangeResult<Person> personFlux = webTestClient
                .get().uri("/react/persons")
                .accept(MediaType.APPLICATION_JSON) // text/event-stream or application/stream+json
                .exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .returnResult(Person.class);

        personFlux.getResponseBody().delaySubscription(Duration.ofSeconds(0)).toStream().forEach(System.out::println);
    }

    @Ignore
    @Test
    public void getStreamingPersons() throws URISyntaxException {
        FluxExchangeResult<Person> personResult = webTestClient.get().uri("/react/persons/delay/300")
                .accept(MediaType.APPLICATION_STREAM_JSON) //application/stream+json
                .exchange().expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.parseMediaType("application/stream+json;charset=UTF-8"))
                .returnResult(Person.class);

        personResult.getResponseBody().toStream()
                .forEach(person -> System.out.println("WebTestClient: " + person.toString()));

        System.out.println("Ending Test");
    }

    public class ReactWorker extends Thread {
        private String name;
        private Long delay;

        ReactWorker(String name, Long delay) {
            this.name = name;
            this.delay = delay;
        }
        public void run() {
            Flux<Person> personFlux = webTestClient.get().uri("/react/persons/delay/" + delay).accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful().returnResult(Person.class).getResponseBody();
            personFlux.delaySubscription(Duration.ofSeconds(delay)).toStream()
                    .forEach(s -> System.out.println(name + ": " + s));
        }

    }


}