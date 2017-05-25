package com.github.wkennedy;

import com.github.wkennedy.entity.PersonEntity;
import com.github.wkennedy.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaIT {

    private static final Logger log = LoggerFactory.getLogger(JpaIT.class);

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup() {
        personRepository.deleteAll();
    }

    @Test
    public void testPerson() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("John");
        person.setLastName("Doh");
        person = personRepository.save(person);

        Optional<PersonEntity> personFromRepo = personRepository.findById(person.getId());
        assertTrue(personFromRepo.isPresent());
        log.info(personFromRepo.get().toString());
    }
}
