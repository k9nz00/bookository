package ru.semka.bookository.migration.service.impl;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.stereotype.Component;
import ru.semka.bookository.migration.dto.LiquibaseContext;
import ru.semka.bookository.migration.enums.LiquibaseCommand;
import ru.semka.bookository.migration.service.LiquibaseCommandExecutor;

import java.time.ZoneId;
import java.util.Date;

import static ru.semka.bookository.migration.enums.LiquibaseCommand.ROLLBACK;

@Component
public class RollbackExecutor implements LiquibaseCommandExecutor {
    @Override
    public void executeMigrationCommand(SpringLiquibase springLiquibase, Liquibase liquibase, LiquibaseContext context) throws LiquibaseException {
        springLiquibase.setTag(context.getTagToRollBackTo());
        if (context.getTagToRollBackTo() == null || context.getDateToRollBackTo() == null) {
            throw new LiquibaseException("Tag or date time for rolling back can't be null");
        } else if (springLiquibase.getTag() != null) {
            liquibase.rollback(springLiquibase.getTag(), new Contexts(springLiquibase.getContexts()));
        } else {
            Date rollbackToDate = Date.from(context.getDateToRollBackTo().atZone(ZoneId.systemDefault()).toInstant());
            liquibase.rollback(rollbackToDate, new Contexts(springLiquibase.getContexts()), new LabelExpression());
        }
    }

    @Override
    public LiquibaseCommand getCommand() {
        return ROLLBACK;
    }
}
