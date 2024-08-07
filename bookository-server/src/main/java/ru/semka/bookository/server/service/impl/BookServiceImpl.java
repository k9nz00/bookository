package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.mapper.BookMapper;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final CriteriaPredicateFactory<BookCriteriaDto, BookEntity> bookCriteriaPredicateFactory;
    private final BookMapper bookMapper;

    @Override
    public BookUiDto save(BookRequestDto dto) {
        return bookMapper.bookEntityToBookUiDto(bookDao.save(dto));
    }

    @Override
    public Collection<BookUiDto> getList(BookCriteriaDto criteriaDto) {
        PredicateProvider<BookEntity> predicateProvider = bookCriteriaPredicateFactory.create(criteriaDto);
        Collection<BookEntity> books = bookDao.getBooks(criteriaDto, predicateProvider);
        return books.stream()
                .map(bookMapper::bookEntityToBookUiDto)
                .toList();
    }

    @Override
    public BookUiDto update(int bookId, BookRequestDto dto) {
        BookEntity entity = bookDao.update(bookId, dto);
        return bookMapper.bookEntityToBookUiDto(entity);
    }

    @Override
    public void delete(int bookId) {
        if (bookDao.getDetails(bookId).isEmpty()) {
            throw new ResourceNotFoundException("Книга с id = %d не найдена".formatted(bookId));
        }
        bookDao.deleteBook(bookId);
    }

    @Override
    public BookDetailsUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.getDetails(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Book with id = %d not found", bookId)
                ));
        return bookMapper.bookDetailsEntityToBookDetailsDto(entity);
    }
}
