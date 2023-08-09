package ru.semka.bookository.server.service;

import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;

import java.io.IOException;
import java.util.Collection;

public interface BookService {
    void save(CreateBookRequestDto dto, MultipartFile book, MultipartFile bookCover) throws IOException;

    Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto);

    BookDetailsUiDto getDetails(int bookId);

    String getBookContent(int bookId, int bookContentId);
}
