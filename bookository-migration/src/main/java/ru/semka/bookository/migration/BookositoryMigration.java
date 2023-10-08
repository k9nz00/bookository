package ru.semka.bookository.migration;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("ru.semka.bookository.migration.configuration")
@Log
public class BookositoryMigration {
    public static void main(String[] args) {
        SpringApplication.run(BookositoryMigration.class, args);
    }
}
