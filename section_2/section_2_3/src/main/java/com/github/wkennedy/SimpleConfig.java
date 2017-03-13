package com.github.wkennedy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleConfig {

    @Bean
    public SimpleEndpoint simpleEndpoint() {
        return new SimpleEndpoint("simple", false);
    }
}
