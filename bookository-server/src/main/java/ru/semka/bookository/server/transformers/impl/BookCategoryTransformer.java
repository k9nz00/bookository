package ru.semka.bookository.server.transformers.impl;

import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;
import ru.semka.bookository.server.transformers.Transformer;

@Component
public class BookCategoryTransformer implements Transformer<CategoryEntity, BookCategoryUiDto> {
    @Override
    public BookCategoryUiDto transform(CategoryEntity input) {
        return new BookCategoryUiDto(
                input.getId(),
                input.getName()
        );
    }
}