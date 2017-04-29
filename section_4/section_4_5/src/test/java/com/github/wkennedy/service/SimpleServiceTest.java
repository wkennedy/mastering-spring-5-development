package com.github.wkennedy.service;

import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonMongoRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SimpleServiceTest {

    private Person person = new Person("Doh", "John");

    @Autowired
    private PersonMongoRepository personMongoRepository;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void setUp() throws Exception {
        personMongoRepository.save(person);
    }

    @After
    public void tearDown() throws Exception {
        personMongoRepository.deleteAll();
    }

    @Test
    public void getPersonById() throws Exception {
        Person personFromId = simpleService.getPersonById(person.getId());
        System.out.println(personFromId.toString());

        personFromId = simpleService.getPersonById(person.getId());
        System.out.println(personFromId.toString());

        personFromId.setFirstName("Test");
        simpleService.save(personFromId);

        personFromId = simpleService.getPersonById(person.getId());
        System.out.println(personFromId.toString());

        personFromId = simpleService.getPersonById(person.getId());
        System.out.println(personFromId.toString());
    }

}