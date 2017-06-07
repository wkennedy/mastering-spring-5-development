package com.github.wkennedy;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cql.core.keyspace.DropKeyspaceSpecification;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "section_4_3";
    }

    @Override
    protected List<String> getStartupScripts() {
        String script = "CREATE KEYSPACE IF NOT EXISTS section_4_3 WITH REPLICATION = { 'class' : 'org.apache.cassandra.locator.SimpleStrategy', 'replication_factor': '1' } AND DURABLE_WRITES = true;";
        return Collections.singletonList(script);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Collections.singletonList(DropKeyspaceSpecification.dropKeyspace("section_4_3"));
    }

}
