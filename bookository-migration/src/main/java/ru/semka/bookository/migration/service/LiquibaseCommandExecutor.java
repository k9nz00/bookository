package ru.semka.bookository.migration.service;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import ru.semka.bookository.migration.dto.LiquibaseContext;
import ru.semka.bookository.migration.enums.LiquibaseCommand;

public interface LiquibaseCommandExecutor {
    void executeMigrationCommand(SpringLiquibase springLiquibase,
                                 Liquibase liquibase,
                                 LiquibaseContext context) throws LiquibaseException;

    LiquibaseCommand getCommand();
}
