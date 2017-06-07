package com.github.wkennedy;

import com.github.wkennedy.entity.PersonEntity;
import com.github.wkennedy.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class JpaTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup () {
        personRepository.deleteAll();
    }

    @Test
    public void testPerson() {
        PersonEntity person = generatePerson();
        person = personRepository.save(person);
        Optional<PersonEntity> personFromRepo = personRepository.findById(person.getId());
        assertTrue(personFromRepo.isPresent());
        System.out.println(personFromRepo.get().toString());
    }

    private PersonEntity generatePerson() {
        PersonEntity person = new PersonEntity();
        person.setFirstName("John");
        person.setLastName("Doh");
        return person;
    }
}
