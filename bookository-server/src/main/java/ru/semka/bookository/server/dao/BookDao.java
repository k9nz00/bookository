package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.CreateBookRequestDto;

public interface BookDao {
    @Transactional
    BookEntity save(CreateBookRequestDto dto);

    BookEntity find(int bookId);
}
