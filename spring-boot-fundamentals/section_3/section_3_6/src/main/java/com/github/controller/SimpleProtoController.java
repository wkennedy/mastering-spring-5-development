package com.github.controller;

import com.github.proto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleProtoController {

    @GetMapping("/proto/person")
    public byte[] getPerson() {
        Person.PersonProto person = Person.PersonProto
                .newBuilder()
                .setFirstName("Ted")
                .setLastName("Hardy")
                .setId(23284)
                .build();

        return person.toByteArray();
    }
}
