package ru.semka.bookository.migration.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.semka.bookository.migration.enums.LiquibaseCommand;
import ru.semka.bookository.migration.enums.MigrationDbProfile;

@EqualsAndHashCode(callSuper = false)
@Getter
public class MigrationRollbackContext extends MigrationContext {
    private final String tag;

    public MigrationRollbackContext(MigrationDbProfile dbProfile,
                                    String jdbcUrl,
                                    String database,
                                    String username,
                                    String password,
                                    boolean releaseLocks,
                                    String tag) {
        super(dbProfile, jdbcUrl, database, username, password, releaseLocks, LiquibaseCommand.ROLLBACK);
        this.tag = tag;
    }
}
