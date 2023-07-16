package ru.semka.bookository.migration.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("database-connection-properties")
@Data
public class DatabaseConnectionProperties {
    private final String driverClassName;
    private final String jdbcUrl;
    private final String database;
    private final String schema;
    private final String login;
    private final String password;
    private final String poolName;
    private final int maxPoolSize;
}
