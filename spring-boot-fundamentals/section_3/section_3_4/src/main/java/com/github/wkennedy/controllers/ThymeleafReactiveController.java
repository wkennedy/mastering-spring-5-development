package com.github.wkennedy.controllers;

import com.github.wkennedy.dto.Person;
import com.github.wkennedy.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class ThymeleafReactiveController {

    private final SimpleService simpleService;

    @Autowired
    public ThymeleafReactiveController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @RequestMapping("/personsTemplate")
    public String getPersons(final Model model) {
        final Flux<Person> personFlux = simpleService.getPersonsWithDelay(200L);
        model.addAttribute("dataSource", new ReactiveDataDriverContextVariable(personFlux, 1));

        return "thymeleaf/persons";
    }

    @RequestMapping("/personsChunkedTemplate")
    public String getPersonsChunked(final Model model) {
        final Flux<Person> playlistStream = simpleService.getPersons();
        final List<Person> playlistEntries = playlistStream.collectList().block();

        model.addAttribute("dataSource", playlistEntries);

        return "thymeleaf/persons";
    }
}
