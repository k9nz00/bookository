package ru.semka.bookository.server.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;

import java.io.IOException;
import java.util.Collection;

public interface BookService {
    @Transactional
    BookEntity save(BookRequestDto dto) throws IOException;

    BookUiDto update(int bookId, BookRequestDto dto);

    void deleteBookContent(int bookId, int bookContentId);

    void deleteBook(int bookId);

    void attachBook(int bookId, MultipartFile book) throws IOException;

    Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto);

    BookDetailsUiDto getDetails(int bookId);

    String getBookContent(int bookId, int bookContentId);

    void updateBookCover(int bookId, MultipartFile cover) throws IOException;

    void deleteBookCover(int bookId);
}
