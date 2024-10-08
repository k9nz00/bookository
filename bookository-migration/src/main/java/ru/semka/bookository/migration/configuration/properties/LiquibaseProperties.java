package ru.semka.bookository.migration.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("liquibase")
@Data
public class LiquibaseProperties {
    private String changeLogPath;
    private String defaultSchema;
}
