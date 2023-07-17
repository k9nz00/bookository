package ru.semka.bookository.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@SpringBootConfiguration
@EnableConfigurationProperties
public class BookositoryServer {
    public static void main(String[] args) {
        SpringApplication.run(BookositoryServer.class, args);
    }
}
