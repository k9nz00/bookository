package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.entity.BookContentInfoEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;

import java.io.IOException;
import java.util.Collection;

public interface BookDao {
    @Transactional
    BookEntity save(CreateBookRequestDto dto);

    @Transactional
    void saveBookContent(int bookId, MultipartFile book, BookFormat bookFormat) throws IOException;

    Collection<BookEntity> getBooks(BookCriteriaDto criteriaDto);

    BookDetailsEntity find(int bookId);

    byte[] getBookContent(int bookId, int bookContentId);

    Collection<BookContentInfoEntity> getContentInfo(int bookId);
}
