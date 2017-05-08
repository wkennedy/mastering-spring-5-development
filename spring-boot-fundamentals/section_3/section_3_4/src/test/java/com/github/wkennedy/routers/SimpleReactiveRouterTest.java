package com.github.wkennedy.routers;

import com.github.wkennedy.dto.Person;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebTestClient
public class SimpleReactiveRouterTest {

    private WebTestClient webTestClient;

    @Autowired
    private SimpleReactiveRouter simpleReactiveRouter;

    @Before
    public void before() {
        this.webTestClient = WebTestClient.bindToRouterFunction(simpleReactiveRouter.routerFunction()).build();
    }

    @Test
    public void routerFunction() throws Exception {
        Flux<Person> personFlux = webTestClient.get().uri("/react/functional/persons").accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().is2xxSuccessful()
                .returnResult(Person.class).getResponseBody();

        personFlux.delaySubscription(Duration.ofSeconds(0))
                .toStream()
                .forEach(s -> System.out.println(1 + ": " + s));
    }

}