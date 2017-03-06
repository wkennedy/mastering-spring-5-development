package com.github.wkennedy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(SimpleProperties.class)
@EnableWebMvc
public class SimpleConfig extends WebMvcConfigurerAdapter {

    @Value("${my.property}")
    private String myProperty;

    private SimpleProperties simpleProperties;

    @Autowired
    public SimpleConfig(SimpleProperties simpleProperties) {
        this.simpleProperties = simpleProperties;
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println(myProperty);
        System.out.println(simpleProperties.getName());
        System.out.println(simpleProperties.isEnabled());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleInterceptor()).addPathPatterns("/**");
    }
}
