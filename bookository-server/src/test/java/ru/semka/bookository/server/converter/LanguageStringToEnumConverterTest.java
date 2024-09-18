package ru.semka.bookository.server.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.semka.bookository.server.common.enums.Language;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LanguageStringToEnumConverterTest {

    private final LanguageStringToEnumConverter converter = new LanguageStringToEnumConverter();

    @ParameterizedTest
    @ValueSource(strings = {"RU", "EN"})
    void convert(String source) {
        Language convertedValue = converter.convert(source);
        assertEquals(Language.valueOf(source), convertedValue);
    }

    @Test
    void convertWithException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> converter.convert("RU1"));

        assertEquals("Invalid language value: RU1. Available values: [RU, EN]", exception.getMessage());
    }
}
