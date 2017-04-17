package com.github.controller;

import com.github.avro.Person;
import com.github.service.PersonService;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleAvroController.class)
public class SimpleAvroControllerTest {

    @MockBean
    PersonService personService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPerson() throws Exception {
        byte[] result = this.mvc.perform(get("/avro/person").accept(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();

        SpecificDatumReader<Person> reader = new SpecificDatumReader<>(Person.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(result, null);
        Person person = reader.read(null, decoder);
        System.out.println(person.getSchema().toString(true));
        System.out.println(person.getId());
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
    }

}