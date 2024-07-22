package ru.semka.bookository.migration.configuration;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.semka.bookository.migration.configuration.properties.SeedingDataProperties;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataAfterMigration implements ApplicationRunner {

    private final SpringLiquibase springLiquibase;
    private final SeedingDataProperties seedingDataProperties;

    @Override
    public void run(ApplicationArguments args) throws LiquibaseException {
        if (seedingDataProperties.isEnable()) {
            springLiquibase.setChangeLog(seedingDataProperties.getChangeLog());
            springLiquibase.afterPropertiesSet();
            log.info("Тестовые данные успешно мигрированы!");
        }
    }
}
