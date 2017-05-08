package com.github.wkennedy.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "Hello!";
    }

    @RequestMapping(path = "/{param}", method = RequestMethod.POST, consumes = "text/plain")
    public String postSimpleValue(@PathVariable(value = "param") String param) {
        return "You successfully posted: " + param;
    }
}
