package ru.semka.bookository.server.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.semka.bookository.server.common.enums.Language;

import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
public class BookUiDto {
    private final Integer id;
    private final String name;
    private final String author;
    private final String genre;
    private final String description;
    private final String annotation;
    private final Boolean isAvailable;
    private final Language language;
    private final Set<String> categories;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
    private final Timestamp deletedAt;
    private final String bigPreview;
}
