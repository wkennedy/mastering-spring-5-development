package com.github.wkennedy;

import com.github.wkennedy.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/")
    public String index() {
        log.debug("In index method in SimpleController");
        return simpleService.getName();
    }

}
