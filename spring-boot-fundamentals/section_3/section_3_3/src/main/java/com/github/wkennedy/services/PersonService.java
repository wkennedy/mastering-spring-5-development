package com.github.wkennedy.services;

import com.github.wkennedy.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    public List<Person> getPersons() {
        List<Person> personList = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");

        personList.add(person);
        personList.add(person2);

        return personList;
    }
}
