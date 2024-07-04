package ru.semka.bookository.server.service;

import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;

import java.util.Collection;

public interface CategoryService {
    CategoryUiDto save(String categoryName);

    CategoryUiDto update(int categoryId, String categoryName);

    void delete(int categoryId);

    Collection<CategoryUiDto> getAll();
}
