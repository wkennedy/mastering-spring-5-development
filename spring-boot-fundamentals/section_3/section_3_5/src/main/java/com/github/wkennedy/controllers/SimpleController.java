package com.github.wkennedy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping("/hello")
    public String hello() {
        log.info("Successful call to /hello");
        return "Hello!\n";
    }
}
