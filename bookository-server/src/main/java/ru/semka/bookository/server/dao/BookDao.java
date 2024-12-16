package ru.semka.bookository.server.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;

import java.util.Collection;
import java.util.Optional;

public interface BookDao {
    @Transactional
    BookEntity save(BookEntity entity);

    @Transactional
    void deleteBook(int bookId);

    Collection<BookEntity> getBooks(BookCriteriaDto criteriaDto, PredicateProvider<BookEntity> predicateProvider);

    Optional<BookDetailsEntity> getDetails(int bookId);

    Optional<BookEntity> get(int bookId);
}
