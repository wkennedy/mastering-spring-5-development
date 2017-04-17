package com.github.controller;

import com.github.proto.Person;
import com.github.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleProtoController.class)
public class SimpleProtoControllerTest {

    @MockBean
    PersonService personService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPerson() throws Exception {
        byte[] result = this.mvc.perform(get("/proto/person").accept(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();

        Person.PersonProto person = Person.PersonProto.parseFrom(result);
        System.out.println("Serialized size: " + person.getSerializedSize());
        System.out.println(person.getId());
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
    }

}