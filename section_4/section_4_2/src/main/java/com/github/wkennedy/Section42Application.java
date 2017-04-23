package com.github.wkennedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
//import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
//@Import(SpringDataRestConfiguration.class)
public class Section42Application {

	public static void main(String[] args) {
		SpringApplication.run(Section42Application.class, args);
	}
}
