package ru.semka.bookository.server.converter;


import org.springframework.core.convert.converter.Converter;
import ru.semka.bookository.server.common.enums.Language;

public class LanguageStringToEnumConverter implements Converter<String, Language> {
    @Override
    public Language convert(String source) {
        return Language.valueOf(source.toUpperCase());
    }
}
