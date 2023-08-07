package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;

import java.io.IOException;

public interface BookDao {
    @Transactional
    BookEntity save(CreateBookRequestDto dto);

    @Transactional
    void saveBookContent(int bookId, MultipartFile book) throws IOException;

    BookDetailsEntity find(int bookId);
}
