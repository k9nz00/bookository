package ru.semka.bookository.server.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;

import java.io.IOException;
import java.util.Collection;

public interface BookService {
    @Transactional
    BookUiDto save(BookRequestDto dto);

    BookUiDto update(int bookId, BookRequestDto dto);

    void deleteBook(int bookId);

    Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto);

    BookDetailsUiDto getDetails(int bookId);

    void saveBookCover(int bookId, MultipartFile cover) throws IOException;

    void deleteBookCover(int bookId);
}
