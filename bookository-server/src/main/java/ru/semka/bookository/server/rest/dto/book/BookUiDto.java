package ru.semka.bookository.server.rest.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

import java.sql.Timestamp;
import java.util.Collection;

@Data
@AllArgsConstructor
public class BookUiDto {
    private final Integer id;
    private final String name;
    private final String author;
    private final String genre;
    private final String annotation;
    private final Boolean isAvailable;
    private final Language language;
    private final Collection<CategoryUiDto> categories;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
    private final String smallPreview;
}
