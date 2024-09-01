package ru.semka.bookository.migration.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.semka.bookository.migration.enums.MigrationDbProfile;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MigrationUtil {
    private static final String CHANGE_LOG_FILE_TEMPLATE = "changelog-%s.yml";

    public static String createChangeLogName(String changeLogDirectoryPath, MigrationDbProfile dbProfile) {
        String changeLogFileName = String.format(CHANGE_LOG_FILE_TEMPLATE, dbProfile.getValue());
        return changeLogDirectoryPath + (changeLogDirectoryPath.endsWith(File.separator) ? "" : File.separator) + changeLogFileName;
    }
}
