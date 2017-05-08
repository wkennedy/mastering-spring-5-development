package com.github.wkennedy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    private static final Logger log = LoggerFactory.getLogger(SimpleService.class);

    public String getName() {
        log.trace("Returning name of SimpleService");
        return "SimpleService";
    }
}
