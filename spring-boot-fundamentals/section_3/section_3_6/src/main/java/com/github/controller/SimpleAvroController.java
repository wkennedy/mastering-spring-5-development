package com.github.controller;

import com.github.avro.Person;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class SimpleAvroController {

    @GetMapping("/avro/person")
    public byte[] getPerson() throws IOException {
        Person person = new Person();
        person.setId(323984);
        person.setFirstName("Ted");
        person.setLastName("Hardy");

        DatumWriter<Person> userDatumWriter = new SpecificDatumWriter<>(Person.class);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        userDatumWriter.write(person, encoder);
        encoder.flush();
        return outputStream.toByteArray();
    }
}
