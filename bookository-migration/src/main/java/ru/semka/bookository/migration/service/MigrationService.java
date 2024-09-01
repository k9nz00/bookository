package ru.semka.bookository.migration.service;

import ru.semka.bookository.migration.dto.MigrationRollbackContext;
import ru.semka.bookository.migration.dto.MigrationUpdateContext;

public interface MigrationService {
    void update(MigrationUpdateContext context);

    void rollback(MigrationRollbackContext context);
}
