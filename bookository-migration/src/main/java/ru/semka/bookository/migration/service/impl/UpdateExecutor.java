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

import static ru.semka.bookository.migration.enums.LiquibaseCommand.UPDATE;

@Component
public class UpdateExecutor implements LiquibaseCommandExecutor {
    @Override
    public void executeMigrationCommand(SpringLiquibase springLiquibase, Liquibase liquibase, LiquibaseContext context) throws LiquibaseException {
        if (springLiquibase.isClearCheckSums()) {
            liquibase.clearCheckSums();
        }

        if (springLiquibase.getTag() != null) {
            liquibase.update(
                    springLiquibase.getTag(),
                    new Contexts(springLiquibase.getContexts()),
                    new LabelExpression(springLiquibase.getLabelFilter())
            );
        } else {
            liquibase.update(
                    new Contexts(springLiquibase.getContexts()),
                    new LabelExpression(springLiquibase.getLabelFilter())
            );
        }
    }

    @Override
    public LiquibaseCommand getCommand() {
        return UPDATE;
    }
}
