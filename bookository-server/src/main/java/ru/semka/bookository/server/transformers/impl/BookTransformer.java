package ru.semka.bookository.server.transformers.impl;

import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.dao.entity.SmallBookPreviewEntity;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;
import ru.semka.bookository.server.transformers.Transformer;

import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@Component
public class BookTransformer implements Transformer<BookEntity, BookUiDto> {
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public BookUiDto transform(BookEntity input) {
        return new BookUiDto(
                input.getId(),
                input.getName(),
                input.getAuthor(),
                input.getGenre(),
                input.getAnnotation(),
                input.getIsAvailable(),
                input.getLanguage(),
                getCategories(input.getCategories()),
                input.getCreatedAt(),
                input.getUpdatedAt(),
                getPreviewContent(input.getSmallPreview())
        );
    }


    private String getPreviewContent(SmallBookPreviewEntity bookSmallPreview) {
        return Optional.ofNullable(bookSmallPreview)
                .map(el -> encoder.encodeToString(el.getPreview()))
                .orElse(null);
    }

    private Collection<BookCategoryUiDto> getCategories(Collection<CategoryEntity> categories) {
        return categories.stream()
                .map(entity -> new BookCategoryUiDto(entity.getId(), entity.getName()))
                .toList();
    }
}
