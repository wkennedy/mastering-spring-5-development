package com.github.wkennedy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.reactor.core.ReactorCoreAutoConfiguration;

@SpringBootApplication(exclude = {ReactorCoreAutoConfiguration.class})
public class Section21Application {

	public static void main(String[] args) {
		SpringApplication.run(Section21Application.class, args);
	}
}
