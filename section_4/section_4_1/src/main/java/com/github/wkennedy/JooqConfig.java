package com.github.wkennedy;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    private final DataSource dataSource2;

    @Autowired
    public JooqConfig(@Qualifier("dataSource2") DataSource dataSource2) {
        this.dataSource2 = dataSource2;
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource2));
    }

    @Bean
    public DefaultDSLContext dslContext() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider());
        jooqConfiguration.setSQLDialect(SQLDialect.MYSQL);

        return new DefaultDSLContext(jooqConfiguration);
    }


}
