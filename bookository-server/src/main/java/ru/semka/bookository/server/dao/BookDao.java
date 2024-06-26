package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface BookDao {
    @Transactional
    BookEntity save(BookRequestDto dto);

    @Transactional
    BookWithSmallPreviewEntity update(int bookId, BookRequestDto dto);

    @Transactional
    void saveBookContent(int bookId, MultipartFile book, BookFormat bookFormat) throws IOException;

    @Transactional
    void deleteBook(int bookId);

    @Transactional
    void deleteBookContent(int bookId, int bookContentId);

    Collection<BookWithSmallPreviewEntity> getBooks(BookCriteriaDto criteriaDto,
                                                    PredicateProvider<BookWithSmallPreviewEntity> predicateProvider);

    Optional<BookDetailsEntity> getDetails(int bookId);

    Optional<byte[]> getBookContent(int bookId, int bookContentId);
}
