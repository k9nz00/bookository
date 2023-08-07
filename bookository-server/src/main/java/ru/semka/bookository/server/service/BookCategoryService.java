package ru.semka.bookository.server.service;

import ru.semka.bookository.server.rest.dto.bookcategory.BookCategoryUiDto;

import java.util.Collection;

public interface BookCategoryService {
    BookCategoryUiDto save(String categoryName);

    void update(int categoryId, String categoryName);

    Collection<BookCategoryUiDto> getAll();
}
