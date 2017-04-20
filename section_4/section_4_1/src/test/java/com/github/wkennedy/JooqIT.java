package com.github.wkennedy;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.util.maven.example.tables.records.PersonRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.jooq.util.maven.example.tables.Person.PERSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqIT {

    @Autowired
    private DSLContext dslContext;

    @Test
    public void testJooq() {
        dslContext.insertInto(PERSON).set(PERSON.FIRST_NAME, "John").set(PERSON.LAST_NAME, "Doe").execute();
        Result<PersonRecord> persons = dslContext.selectFrom(PERSON).where(PERSON.FIRST_NAME.equal("John")).fetch();

        System.out.println(persons.get(0).toString());
    }
}
