package com.github;

import com.github.thrift.PersonService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Section36Application {

	public static void main(String[] args) {
		SpringApplication.run(Section36Application.class, args);
	}

	@Bean
	public TProtocolFactory tProtocolFactory() {
		return new TBinaryProtocol.Factory();
	}

	@Bean
	public ServletRegistrationBean<TServlet> servletRegistrationBean(com.github.service.PersonService personService, TProtocolFactory protocolFactory){
		TServlet tServlet = new TServlet(new PersonService.Processor<>(personService), protocolFactory);
		return new ServletRegistrationBean<>(tServlet,"/thrift/*");
	}
}
