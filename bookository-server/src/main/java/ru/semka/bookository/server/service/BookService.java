package ru.semka.bookository.server.service;

import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;

import java.util.Collection;

public interface BookService {

    @Transactional
    BookUiDto save(BookRequestDto dto);

    @Transactional
    Integer saveFromFile(MultipartFile file);

    BookUiDto update(int bookId, BookRequestDto dto);

    void delete(int bookId);

    Collection<BookUiDto> getList(BookCriteriaDto criteriaDto);

    BookDetailsUiDto getDetails(int bookId);

}
