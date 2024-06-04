package ru.semka.bookository.server.service;

import ru.semka.bookository.server.dao.entity.CategoryEntity;
import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;

import java.util.Collection;

public interface BookCategoryService {
    BookCategoryUiDto save(String categoryName);

    CategoryEntity update(int categoryId, String categoryName);

    void delete(int categoryId);

    Collection<BookCategoryUiDto> getAll();
}
