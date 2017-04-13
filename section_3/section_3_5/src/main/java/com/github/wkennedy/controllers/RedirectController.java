package com.github.wkennedy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/redirect")
    public String redirect() {
        System.out.println("In redirect");
        return "redirect:/hello";
    }
}
