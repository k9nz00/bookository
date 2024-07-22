package ru.semka.bookository.migration.configuration.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("seeding-data")
@RequiredArgsConstructor
public class SeedingDataProperties {
    private final boolean enable;
    private final String changeLog;
}
