package com.github.wkennedy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Section35ApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(Section35ApplicationTests.class);

	@LocalServerPort
	private int port;

	@Test
	public void contextLoads() {
		log.info("Unit test random port: " + port);
		assertNotNull(port);
	}

}