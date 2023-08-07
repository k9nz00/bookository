package ru.semka.bookository.server.transformers.impl;

import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.transformers.Transformer;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookDetailsTransformer implements Transformer<BookDetailsEntity, BookUiDto> {
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public BookUiDto transform(BookDetailsEntity input) {
        return new BookUiDto(
                input.getId(),
                input.getName(),
                input.getAuthor(),
                input.getGenre(),
                input.getDescription(),
                input.getAnnotation(),
                input.getIsAvailable(),
                input.getLanguage(),
                getCategoryNames(input.getCategories()),
                input.getCreatedAt(),
                input.getUpdatedAt(),
                input.getDeletedAt(),
                encoder.encodeToString(input.getBigPreview().getPreview())
        );
    }

    private Set<String> getCategoryNames(List<CategoryEntity> categories) {
        return categories.stream()
                .map(CategoryEntity::getName)
                .collect(Collectors.toSet());
    }

}