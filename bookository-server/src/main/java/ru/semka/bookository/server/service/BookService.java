package ru.semka.bookository.server.service;

import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;

import java.util.Collection;

public interface BookService {
    BookUiDto save(BookRequestDto dto);

    BookUiDto update(int bookId, BookRequestDto dto);

    void delete(int bookId);

    Collection<BookUiDto> getList(BookCriteriaDto criteriaDto);

    BookDetailsUiDto getDetails(int bookId);

}
