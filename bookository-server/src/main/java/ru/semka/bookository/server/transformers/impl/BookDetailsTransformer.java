package ru.semka.bookository.server.transformers.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.entity.BookContentInfoEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.book.BookContentInfoUiDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;
import ru.semka.bookository.server.transformers.Transformer;
import ru.semka.bookository.server.transformers.wrapper.BookDetailsWrapper;

import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Component
public class BookDetailsTransformer implements Transformer<BookDetailsWrapper, BookDetailsUiDto> {
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public BookDetailsUiDto transform(BookDetailsWrapper input) {
        BookDetailsEntity book = input.getBook();
        return new BookDetailsUiDto(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getAnnotation(),
                book.getIsAvailable(),
                book.getLanguage(),
                getCategories(book.getCategories()),
                book.getCreatedAt(),
                book.getUpdatedAt(),
                getContentInfo(input.getBookContentInfoEntities()),
                getPreview(book.getBigPreview() != null ? book.getBigPreview().getPreview() : null)
        );
    }

    private List<BookCategoryUiDto> getCategories(List<CategoryEntity> categories) {
        return categories.stream()
                .map(entity -> new BookCategoryUiDto(entity.getId(), entity.getName()))
                .toList();
    }

    private Collection<BookContentInfoUiDto> getContentInfo(Collection<BookContentInfoEntity> infoEntities) {
        return infoEntities.stream()
                .map(entity -> new BookContentInfoUiDto(
                        entity.getId(),
                        entity.getBookId(),
                        FileUtils.byteCountToDisplaySize(entity.getSize()),
                        entity.getBookFormat()
                ))
                .toList();
    }

    private String getPreview(byte[] bytes) {
        return "data:image/gpeg;base64," + encoder.encodeToString(bytes);
    }
}