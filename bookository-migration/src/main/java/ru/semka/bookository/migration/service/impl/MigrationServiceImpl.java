package ru.semka.bookository.migration.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.semka.bookository.migration.configuration.properties.LiquibaseProperties;
import ru.semka.bookository.migration.dto.LiquibaseContext;
import ru.semka.bookository.migration.dto.MigrationContext;
import ru.semka.bookository.migration.dto.MigrationRollbackContext;
import ru.semka.bookository.migration.dto.MigrationUpdateContext;
import ru.semka.bookository.migration.exeption.MigrationException;
import ru.semka.bookository.migration.factory.DataSourceFactory;
import ru.semka.bookository.migration.service.LiquibaseService;
import ru.semka.bookository.migration.service.MigrationService;
import ru.semka.bookository.migration.util.MigrationUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MigrationServiceImpl implements MigrationService {

    private final LiquibaseProperties liquibaseProperties;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public MigrationServiceImpl(LiquibaseProperties liquibaseProperties, DataSourceFactory dataSourceFactory) {
        this.liquibaseProperties = liquibaseProperties;
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public void update(MigrationUpdateContext context) {
        DataSource dataSource = dataSourceFactory.create(context.getDbProfile(),
                context.getJdbcUrl(),
                context.getDatabase(),
                context.getUsername(),
                context.getPassword());
        LiquibaseContext liquibaseContext = createLiquibaseContext(context);
        execute(dataSource, liquibaseContext);
    }

    @Override
    public void rollback(MigrationRollbackContext context) {

    }


    private void execute(DataSource dataSource, LiquibaseContext liquibaseContext) {
        try {
            LiquibaseService liquibaseService = new LiquibaseServiceImpl(dataSource, liquibaseContext);
            liquibaseService.execute();
            log.info(
                    "Миграция успешно проведена для профиля {} и команды {}",
                    liquibaseContext.getDbProfile().getValue(),
                    liquibaseContext.getLiquibaseCommand().name()
            );
        } catch (Exception exception) {
            log.error("Возникла ошибка в процессе миграции", exception);
            throw new MigrationException(exception.getMessage(), exception);
        }
    }

    private LiquibaseContext createLiquibaseContext(MigrationUpdateContext context) {
        LiquibaseContext liquibaseContext = LiquibaseContext.builder()
                .dbProfile(context.getDbProfile())
                .liquibaseCommand(context.getLiquibaseCommand())
                .changeLog(MigrationUtil.createChangeLogName(liquibaseProperties.getChangeLogPath(), context.getDbProfile()))
                .schema(liquibaseProperties.getDefaultSchema())
                .changeLogParams(createChangeLogParamMap(context))
                .releaseLocks(context.isReleaseLocks())
                .build();

        return liquibaseContext;
    }

    private Map<String, String> createChangeLogParamMap(MigrationContext context) {
        Map<String, String> changeLogParams = new HashMap<>();
        changeLogParams.put("database", context.getDatabase());

        return changeLogParams;
    }
}
