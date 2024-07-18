package ru.semka.bookository.server.rest.dto.book;

import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

import java.sql.Timestamp;
import java.util.Collection;


public record BookUiDto(
        Integer id,
        String name,
        String author,
        String genre,
        String annotation,
        Boolean isAvailable,
        Language language,
        Collection<CategoryUiDto> categories,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
