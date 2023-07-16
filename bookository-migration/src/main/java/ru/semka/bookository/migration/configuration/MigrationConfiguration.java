package ru.semka.bookository.migration.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.semka.bookository.migration.configuration.properties.DatabaseConnectionProperties;

import javax.sql.DataSource;

@Configuration
public class MigrationConfiguration {
    @Bean
    public DataSource dataSource(DatabaseConnectionProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getDriverClassName());
        config.setJdbcUrl(properties.getJdbcUrl());
        config.setSchema(properties.getSchema());
        config.setUsername(properties.getLogin());
        config.setPassword(properties.getPassword());
        config.setPoolName(properties.getPoolName());
        config.setMaximumPoolSize(properties.getMaxPoolSize());
        config.setConnectionTestQuery("SELECT 1");
        return new HikariDataSource(config);
    }
}