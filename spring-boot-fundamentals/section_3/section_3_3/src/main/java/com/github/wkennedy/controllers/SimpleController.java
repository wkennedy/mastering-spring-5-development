package com.github.wkennedy.controllers;

import com.github.wkennedy.dto.Person;
import com.github.wkennedy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SimpleController {

    private final PersonService personService;

    @Autowired
    public SimpleController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/personList")
    public String content1() {
        return "personList";
    }

    @ModelAttribute("allPersons")
    public List<Person> populatePersons() {
        return personService.getPersons();
    }

    @ModelAttribute("simpleValue")
    public String populateSimpleValue() {
        return "Hello!";
    }

    @ModelAttribute("today")
    public LocalDate localDate() {
        return LocalDate.now();
    }
}
