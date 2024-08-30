package ru.semka.bookository.migration.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MigrationDbProfile {
    DEFAULT("default", "org.postgresql.Driver"),
    SEEDDATA("seed-data", "org.postgresql.Driver");

    private final String value;
    private final String driverClassName;

    public static MigrationDbProfile fromValue(String value) {
        for (MigrationDbProfile dbProfile : MigrationDbProfile.values()) {
            if (dbProfile.getValue().equals(value)) {
                return dbProfile;
            }
        }
        throw new IllegalArgumentException("Couldn't find corresponding db profile for value: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
