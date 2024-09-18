package ru.semka.bookository.server.service;

import ru.semka.bookository.server.rest.dto.bookcategory.CategoryUiDto;
import ru.semka.bookository.server.rest.dto.bookcategory.CreateBookCategoriesRequestDto;
import ru.semka.bookository.server.rest.dto.bookcategory.UpdateBookCategoriesRequestDto;

import java.util.Collection;

public interface CategoryService {
    CategoryUiDto save(CreateBookCategoriesRequestDto dto);

    CategoryUiDto update(int categoryId, UpdateBookCategoriesRequestDto dto);

    void delete(int categoryId);

    Collection<CategoryUiDto> getAll();
}
