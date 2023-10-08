package ru.semka.bookository.server.common.enums;

import java.util.Arrays;

public enum Language {
    RU,
    EN;

    public static Language fromValue(String input) {
        for (Language value : Language.values()) {
            if (value.name().equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Invalid language value: %s. Available values: %s",
                input,
                Arrays.toString(Arrays.stream(Language.values()).map(Language::name).toArray())
        ));
    }
}
