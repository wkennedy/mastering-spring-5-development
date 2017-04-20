package com.github.wkennedy;

import com.github.wkennedy.entity.PersonEntity;
import com.github.wkennedy.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaIT {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testPerson() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("John");
        person.setLastName("Doh");
        person = personRepository.save(person);
        Optional<PersonEntity> personFromRepo = personRepository.findOne(person.getId());
        assertTrue(personFromRepo.isPresent());
        System.out.println(personFromRepo.get().toString());
    }
}
