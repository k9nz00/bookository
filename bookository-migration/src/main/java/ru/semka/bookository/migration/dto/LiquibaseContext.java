package ru.semka.bookository.migration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import ru.semka.bookository.migration.model.LiquibaseCommand;
import ru.semka.bookository.migration.model.MigrationDbProfile;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
public class LiquibaseContext {
    private final MigrationDbProfile dbProfile;
    private final LiquibaseCommand liquibaseCommand;
    @NonNull
    private String changeLog;
    private String schema;
    private final boolean shouldRun = true;
    private String tagToRollBackTo;
    private LocalDateTime dateToRollBackTo;
    private Map<String, String> changeLogParams;
    private boolean releaseLocks;
}
