package com.github.wkennedy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "Hello!";
    }
}
