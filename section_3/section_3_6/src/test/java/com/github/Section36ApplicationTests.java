package com.github;

import com.github.thrift.Person;
import com.github.thrift.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Section36ApplicationTests {

	@Autowired
	private TProtocolFactory protocolFactory;

	@LocalServerPort
	private int port;

	private PersonService.Client personClient;

	@Before
	public void setUp() throws Exception {
		TTransport transport = new THttpClient("http://localhost:" + port + "/thrift");
		TProtocol protocol = protocolFactory.getProtocol(transport);
		personClient = new PersonService.Client(protocol);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPerson() throws TException {
		Person person = personClient.getPerson();
		System.out.println(person.getId());
		System.out.println(person.getFirstName());
		System.out.println(person.getLastName());
	}

}
