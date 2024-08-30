package ru.semka.bookository.migration.factory.impl;

import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.stereotype.Component;
import ru.semka.bookository.migration.factory.DataSourceFactory;
import ru.semka.bookository.migration.model.MigrationDbProfile;

import javax.sql.DataSource;
import java.util.Properties;

@Component
public class DataSourceFactoryImpl implements DataSourceFactory {
    @Override
    public DataSource create(MigrationDbProfile dbProfile, String jdbcUrl, String database, String username, String password) {
        return new DriverDataSource(
                jdbcUrl,
                dbProfile.getDriverClassName(),
                new Properties(),
                username,
                password
        );
    }
}
