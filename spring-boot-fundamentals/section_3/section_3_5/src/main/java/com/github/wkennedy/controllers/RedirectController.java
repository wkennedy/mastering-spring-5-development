package com.github.wkennedy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RedirectController {

    private static final Logger log = LoggerFactory.getLogger(RedirectController.class);

    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest httpServletRequest) {
        printHeadersInfo(httpServletRequest);
        return "redirect:/hello";
    }

    @GetMapping("/redirectURL")
    @ResponseBody
    public String getRedirectURL(HttpServletRequest httpRequest) {
        printHeadersInfo(httpRequest);
        return ServletUriComponentsBuilder.fromRequest(httpRequest).path("/hello").build().toUriString();
    }

    private void printHeadersInfo(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headerMap.put(key, value);
        }

        headerMap.forEach((key, value) -> log.info(key + " : " + value));
    }
}
