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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void before() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void testPerson() throws Exception {
        Person person = new Person();
        person.setId(UUIDs.timeBased());
        person.setLastName("Doe");
        person.setFirstName("John");

        personRepository.save(person);
        List<Person> personList = personRepository.findAll();
        personList.size();
    }

}