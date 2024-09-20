package ru.semka.bookository.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("ru.semka.bookository.migration.configuration")
public class BookositoryMigration {
    public static void main(String[] args) {
        SpringApplication.run(BookositoryMigration.class, args);
    }
}
