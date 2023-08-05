package ru.semka.bookository.server.service;

import ru.semka.bookository.server.rest.dto.BookCategoryUiDto;

import java.util.Collection;

public interface BookCategoryService {
    void save(String categoryName);

    Collection<BookCategoryUiDto> getAll();
}
