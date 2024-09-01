package ru.semka.bookository.migration.service.impl;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import ru.semka.bookository.migration.dto.LiquibaseContext;
import ru.semka.bookository.migration.service.LiquibaseCommandExecutor;
import ru.semka.bookository.migration.service.LiquibaseService;

import javax.sql.DataSource;

@Slf4j
public class LiquibaseServiceImpl extends SpringLiquibase implements LiquibaseService {
    private final LiquibaseContext liquibaseContext;
    private final LiquibaseCommandExecutor executor;

    public LiquibaseServiceImpl(DataSource dataSource, LiquibaseContext liquibaseContext, LiquibaseCommandExecutor executor) {
        this.setDataSource(dataSource);
        this.setChangeLog(liquibaseContext.getChangeLog());
        this.setDefaultSchema(liquibaseContext.getSchema());
        this.setShouldRun(liquibaseContext.isShouldRun());
        this.setChangeLogParameters(liquibaseContext.getChangeLogParams());

        this.liquibaseContext = liquibaseContext;
        this.executor = executor;
    }

    @Override
    public void execute() throws LiquibaseException {
        afterPropertiesSet();
    }

    @Override
    protected void performUpdate(Liquibase liquibase) throws LiquibaseException {
        if (liquibaseContext.isReleaseLocks()) {
            liquibase.forceReleaseLocks();
        }
        executor.executeMigrationCommand(this, liquibase, liquibaseContext);
    }
}