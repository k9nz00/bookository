package ru.semka.bookository.migration.converter;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.core.convert.converter.Converter;
import ru.semka.bookository.migration.model.MigrationDbProfile;

import java.util.Arrays;

public class MigrationDbProfileConverter implements Converter<String, MigrationDbProfile> {

    @Override
    public MigrationDbProfile convert(String source) {
        try {
            return MigrationDbProfile.fromValue(source);
        } catch (IllegalArgumentException e) {
            throw new OpenApiResourceNotFoundException(
                    "Не найден профиль миграции для полученного значения - %s. Доступные значениея - %s"
                            .formatted(source, Arrays.toString(MigrationDbProfile.values()))
            );
        }
    }
}
