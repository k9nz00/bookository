package ru.semka.bookository.migration.factory;

import ru.semka.bookository.migration.enums.MigrationDbProfile;

import javax.sql.DataSource;

public interface DataSourceFactory {

    DataSource create(MigrationDbProfile dbProfile,
                      String jdbcUrl,
                      String database,
                      String username,
                      String password);
}
