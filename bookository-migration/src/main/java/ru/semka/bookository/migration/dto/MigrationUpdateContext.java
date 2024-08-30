package ru.semka.bookository.migration.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.semka.bookository.migration.model.LiquibaseCommand;
import ru.semka.bookository.migration.model.MigrationDbProfile;

@EqualsAndHashCode(callSuper = false)
@Getter
public class MigrationUpdateContext extends MigrationContext {
    public MigrationUpdateContext(MigrationDbProfile dbProfile,
                                  String jdbcUrl,
                                  String database,
                                  String username,
                                  String password,
                                  boolean releaseLocks) {
        super(dbProfile, jdbcUrl, database, username, password, releaseLocks, LiquibaseCommand.UPDATE);
    }
}
