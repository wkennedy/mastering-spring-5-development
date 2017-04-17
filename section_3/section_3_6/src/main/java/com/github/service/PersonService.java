package com.github.service;

import com.github.thrift.Person;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements com.github.thrift.PersonService.Iface {

    @Override
    public Person getPerson() throws TException {
        Person person = new Person();
        person.setId(3252);
        person.setFirstName("John");
        person.setLastName("Doh");

        return person;
    }
}
