package com.github.wkennedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
//@ServletComponentScan //needed for @WebFilter
public class Section35Application {

	private static final Logger log = LoggerFactory.getLogger(Section35Application.class);

	@Value("${simpleFilter.enabled}")
	public boolean simpleFilterEnabled;

	public static void main(String[] args) {
		SpringApplication.run(Section35Application.class, args);
	}


//	@Bean
//	public ServletContextInitializer getInitializer() {
//		return new Initializer();
//	}
//
//	public class Initializer implements ServletContextInitializer {
//
//		@Override
//		public void onStartup(ServletContext servletContext) throws ServletException {
//			log.info("Server Info: " + servletContext.getServerInfo());
//		}
//	}

	//This is an alternative to the ServletComponentScan
	@Bean
	public SimpleFilter getSimpleFilter() {
		return new SimpleFilter();
	}

	@Bean
	public FilterRegistrationBean registration(SimpleFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean<>(filter);
		registration.setEnabled(simpleFilterEnabled);
		return registration;
	}

}
