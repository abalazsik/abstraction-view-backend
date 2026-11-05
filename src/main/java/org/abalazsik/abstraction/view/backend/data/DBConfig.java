package org.abalazsik.abstraction.view.backend.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import java.util.List;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRow;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import java.util.ArrayList;
import org.abalazsik.abstraction.view.backend.domain.AbstractionRows;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

/**
 *
 * @author ador
 */
@EnableR2dbcRepositories
@Configuration
public class DBConfig extends AbstractR2dbcConfiguration {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new AbstractionRowsToString(), new StringToAbstractionRows());
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                        .option(DRIVER, "h2")
                        .option(HOST, "localhost")
                        .option(USER, "sa")
                        .option(PASSWORD, "sa")
                        .option(DATABASE, "abstraction-diagram")
                        .build());
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("create_db.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
    
    public static class AbstractionRowsToString implements Converter<AbstractionRows, String> {

        @Override
        public String convert(AbstractionRows source) {
            try {
                return MAPPER.writeValueAsString(source);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static class StringToAbstractionRows implements Converter<String, AbstractionRows> {

        @Override
        public AbstractionRows convert(String source) {
            try {
                ArrayList<AbstractionRow> rows = MAPPER.readValue(source, new TypeReference<ArrayList<AbstractionRow>>() {
                });
                return new AbstractionRows(rows);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("failed to convert value", ex);
            }
        }

    }
}
