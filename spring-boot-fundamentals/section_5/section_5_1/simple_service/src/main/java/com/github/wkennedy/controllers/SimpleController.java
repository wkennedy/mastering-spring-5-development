package com.github.wkennedy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleController {

    private final OAuth2RestTemplate restTemplate;

    @Autowired
    public SimpleController(OAuth2RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/person")
    public String getPerson() {
        return restTemplate.getForObject("http://localhost:8083/resource/person", String.class);
    }
}
