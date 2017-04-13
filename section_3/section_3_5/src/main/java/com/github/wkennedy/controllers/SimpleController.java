package com.github.wkennedy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("in hello method");
        return "Hello!";
    }
}
