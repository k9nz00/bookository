package ru.semka.bookository.migration.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.semka.bookository.migration.configuration.properties.LiquibaseProperties;
import ru.semka.bookository.migration.enums.MigrationDbProfile;
import ru.semka.bookository.migration.util.MigrationUtil;
import ru.semka.bookository.migration.validation.MigrationProfileValidator;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MigrationProfileValidatorImpl implements ConstraintValidator<MigrationProfileValidator, MigrationDbProfile> {

    List<MigrationDbProfile> profiles;

    @Autowired
    public MigrationProfileValidatorImpl(LiquibaseProperties liquibaseProperties, ResourceLoader resourceLoader) {
        profiles = Arrays.stream(MigrationDbProfile.values())
                .filter(migrationDbProfile -> resourceLoader.getResource(MigrationUtil.createChangeLogName(liquibaseProperties.getChangeLogPath(), migrationDbProfile)).exists())
                .sorted()
                .toList();
        log.info("Найдены актуальные профили для миграции {}", profiles);
    }

    @Override
    public boolean isValid(MigrationDbProfile value, ConstraintValidatorContext context) {

        if (profiles.contains(value)) {
            return true;
        }
        List<String> availableValues = profiles.stream()
                .map(MigrationDbProfile::getValue)
                .toList();
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Профиль миграции [%s] не валиден. Доступные значения: [%s]".formatted(value, availableValues))
                .addConstraintViolation();
        return false;
    }
}
