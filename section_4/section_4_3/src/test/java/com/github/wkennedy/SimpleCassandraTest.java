package com.github.wkennedy;

import com.datastax.driver.core.utils.UUIDs;
import com.github.wkennedy.entities.Person;
import com.github.wkennedy.repositories.PersonRepository;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionIntegrationTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest({"spring.data.cassandra.port=9142",
        "spring.data.cassandra.keyspace-name=section_4_3",
        "spring.data.cassandra.schema-action=RECREATE_DROP_UNUSED"})
@TestExecutionListeners({ CassandraUnitDependencyInjectionIntegrationTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(keyspace = "section_4_3", value = "init.cql")
@EmbeddedCassandra(timeout = 60000)
public class SimpleCassandraTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test() {
        Person person = new Person();
        person.setId(UUIDs.timeBased());
        person.setLastName("Doe");
        person.setFirstName("John");

        personRepository.save(person);
        List<Person> personList = personRepository.findAll();
        assertEquals(1, personList.size());
    }
}
