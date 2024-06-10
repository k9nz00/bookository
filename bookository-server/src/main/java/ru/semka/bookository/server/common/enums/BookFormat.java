package ru.semka.bookository.server.common.enums;

import java.util.Arrays;

public enum BookFormat {
    TXT,
    EPUB,
    FB2,
    PDF;

    public static BookFormat fromValue(String input) {
        for (BookFormat value : BookFormat.values()) {
            if (value.name().equals(input)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format(
                "Invalid book format value: %s. Available values: %s",
                input,
                Arrays.toString(Arrays.stream(BookFormat.values()).map(BookFormat::name).toArray())
        ));

    }
}
