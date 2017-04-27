package com.github.wkennedy.repositories;

import com.datastax.driver.core.utils.UUIDs;
import com.github.wkennedy.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonCassandraRepositoryIT {

    @Autowired
    private PersonCassandraRepository personCassandraRepository;

    @Before
    public void before() throws Exception {
        personCassandraRepository.deleteAll();
    }

    @Test
    public void testPerson() throws Exception {
        Person person = new Person();
        person.setId(UUIDs.timeBased());
        person.setLastName("Doe");
        person.setFirstName("John");

        personCassandraRepository.save(person);

        List<Person> personList = personCassandraRepository.findAll();
        assertEquals(1, personList.size());
        Person personFromList = personList.get(0);
        assertEquals("Doe", personFromList.getLastName());
        assertEquals("John", personFromList.getFirstName());
    }

}