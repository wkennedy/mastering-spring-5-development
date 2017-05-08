package com.github.wkennedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@ServletComponentScan //needed for @WebFilter
public class SimpleConfig {

    private static Logger log = LoggerFactory.getLogger(SimpleConfig.class);

    @Value("${simpleFilter.enabled}")
    public boolean simpleFilterEnabled;

	@Bean
	public ServletContextInitializer getInitializer() {
		return new Initializer();
	}

	public class Initializer implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			log.info("Server Info: " + servletContext.getServerInfo());
		}
	}

    //This is an alternative to the ServletComponentScan
//	@Bean
//	public SimpleFilter getSimpleFilter() {
//		return new SimpleFilter();
//	}

//    @Bean
//    public FilterRegistrationBean registerSimpleFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean<>(new SimpleFilter());
//        registration.setEnabled(simpleFilterEnabled);
//        return registration;
//    }

}
