package com.github.wkennedy.repositories;

import com.github.wkennedy.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest()
public class PersonMongoRepositoryIT {

    @Autowired
    private PersonMongoRepository personMongoRepository;

    @Before
    public void setup() {
        personMongoRepository.deleteAll();
    }

    @Test
    public void testMongo() {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setLastName("Doe");
        person.setFirstName("John");

        personMongoRepository.save(person);

        List<Person> personList = personMongoRepository.findAll();
        assertEquals(1, personList.size());
        Person personFromList = personList.get(0);
        assertEquals("Doe", personFromList.getLastName());
        assertEquals("John", personFromList.getFirstName());
    }
}
