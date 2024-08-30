package ru.semka.bookository.migration.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.semka.bookository.migration.model.LiquibaseCommand;
import ru.semka.bookository.migration.model.MigrationDbProfile;

@RequiredArgsConstructor
@Getter
public abstract class MigrationContext {
    private final MigrationDbProfile dbProfile;
    private final String jdbcUrl;
    private final String database;
    private final String username;
    private final String password;
    private final boolean releaseLocks;
    private final LiquibaseCommand liquibaseCommand;
}
