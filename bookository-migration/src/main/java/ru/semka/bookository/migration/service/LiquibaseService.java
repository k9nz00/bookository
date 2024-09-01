package ru.semka.bookository.migration.service;

import liquibase.exception.LiquibaseException;

public interface LiquibaseService {

    void execute() throws LiquibaseException;
}
