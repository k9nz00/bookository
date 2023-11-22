package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BooksCriteriaDto;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface BookDao {
    @Transactional
    BookEntity save(BookRequestDto dto);

    @Transactional
    BookEntity update(int bookId, BookRequestDto dto);

    @Transactional
    void saveBookContent(int bookId, MultipartFile book, BookFormat bookFormat) throws IOException;

    @Transactional
    void deleteBook(int bookId);

    @Transactional
    void deleteBookContent(int bookId, int bookContentId);

    Collection<BookWithSmallPreviewEntity> getBooks(BooksCriteriaDto criteriaDto);

    Optional<BookDetailsEntity> getDetails(int bookId);

    byte[] getBookContent(int bookId, int bookContentId);
}
