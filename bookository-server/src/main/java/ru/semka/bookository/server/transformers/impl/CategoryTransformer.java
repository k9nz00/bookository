package ru.semka.bookository.server.transformers.impl;

import org.springframework.stereotype.Component;
import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.transformers.Transformer;

@Component
public class CategoryTransformer implements Transformer<CategoryEntity, CategoryUiDto> {
    @Override
    public CategoryUiDto transform(CategoryEntity input) {
        return new CategoryUiDto(
                input.getId(),
                input.getName()
        );
    }
}