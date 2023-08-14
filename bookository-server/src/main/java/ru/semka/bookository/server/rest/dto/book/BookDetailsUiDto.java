package ru.semka.bookository.server.rest.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.semka.bookository.server.common.enums.Language;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class BookDetailsUiDto {
    private final Integer id;
    private final String name;
    private final String author;
    private final String genre;
    private final String description;
    private final String annotation;
    private final Boolean isAvailable;
    private final Language language;
    private final List<BookCategoryUiDto> categories;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
    private final Collection<BookContentInfoUiDto> bookContentInfo;
    private final String bigPreview;
}
