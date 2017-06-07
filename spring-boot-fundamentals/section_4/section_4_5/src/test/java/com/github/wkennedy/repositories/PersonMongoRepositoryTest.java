package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PersonMongoRepositoryTest {

    @Autowired
    private PersonMongoRepository personMongoRepository;

    private Person person = new Person("Doh", "John");

    @Before
    public void setUp() throws Exception {
        personMongoRepository.save(person);
    }

    @After
    public void tearDown() throws Exception {
        personMongoRepository.deleteAll();
    }

    @Test
    public void basicTest() throws Exception {
        Person personFromId = personMongoRepository.findById(person.getId()).get();
        System.out.println(personFromId.toString());

        personFromId = personMongoRepository.findById(person.getId()).get();
        System.out.println(personFromId.toString());

        personFromId.setFirstName("Test");
        personMongoRepository.save(personFromId);

        Thread.sleep(2000);

        personFromId = personMongoRepository.findById(person.getId()).get();
        System.out.println(personFromId.toString());

        personFromId = personMongoRepository.findById(person.getId()).get();
        System.out.println(personFromId.toString());
    }

}