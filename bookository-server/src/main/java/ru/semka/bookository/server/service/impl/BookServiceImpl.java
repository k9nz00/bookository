package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.AuthorDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.CategoryDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.mapper.BookMapper;
import ru.semka.bookository.server.rest.dto.book.*;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.service.CsvService;
import ru.semka.bookository.server.util.ComponentCommonUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final CsvService csvService;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final CategoryDao categoryDao;
    private final CriteriaPredicateFactory<BookCriteriaDto, BookEntity> bookCriteriaPredicateFactory;
    private final BookMapper bookMapper;
    private final ComponentCommonUtil commonUtil;

    @Override
    public BookUiDto save(BookRequestDto dto) {
        BookEntity entity = new BookEntity();
        entity.setCategories(categoryDao.findAllByIdIn(dto.getCategories()));
        entity.setName(dto.getName());
        entity.setAuthors(authorDao.findOrCreate(dto.getAuthors()));
        entity.setGenre(dto.getGenre());
        entity.setAnnotation(dto.getAnnotation());
        entity.setCreatedAt(Timestamp.from(commonUtil.getSystemClock().instant()));
        entity.setIsAvailable(true);
        entity.setLanguage(dto.getLanguage());

        return bookMapper.bookEntityToBookUiDto(bookDao.save(entity));
    }

    @Override
    public Integer saveFromFile(MultipartFile file) {
        Collection<BookCsvDto> bookCsvDtos;
        try (InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream())) {
            bookCsvDtos = csvService.parseCsv(inputStreamReader, BookCsvDto.class);
        } catch (IOException exception) {
            log.error("Ошибка чтения файла %s".formatted(file.getName()));
            bookCsvDtos = Collections.emptyList();
        }

        return bookCsvDtos.stream()
                .map(bookMapper::csvDtoToBookRequestDto)
                .map(this::save)
                .toList()
                .size();
    }

    @Override
    public BookUiDto update(int bookId, BookRequestDto dto) {
        return bookDao.get(bookId)
                .map(entity -> {
                    if (Objects.nonNull(dto.getName())) {
                        entity.setName(dto.getName());
                    }
                    if (Objects.nonNull(dto.getAuthors())) {
                        entity.setAuthors(authorDao.findOrCreate(dto.getAuthors()));
                    }
                    if (Objects.nonNull(dto.getGenre())) {
                        entity.setGenre(dto.getGenre());
                    }
                    if (Objects.nonNull(dto.getLanguage())) {
                        entity.setLanguage(dto.getLanguage());
                    }
                    if (Objects.nonNull(dto.getAnnotation())) {
                        entity.setAnnotation(dto.getAnnotation());
                    }
                    if (!dto.getCategories().isEmpty()) {
                        entity.setCategories(categoryDao.findAllByIdIn(dto.getCategories()));
                    }
                    entity.setUpdatedAt(Timestamp.from(commonUtil.getSystemClock().instant()));
                    return bookMapper.bookEntityToBookUiDto(bookDao.save(entity));
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("Не найдена карточка книги с id = %d".formatted(bookId))
                );
    }

    @Override
    public Collection<BookUiDto> getList(BookCriteriaDto criteriaDto) {
        PredicateProvider<BookEntity> predicateProvider = bookCriteriaPredicateFactory.create(criteriaDto);
        return bookDao.getBooks(criteriaDto, predicateProvider).stream()
                .map(bookMapper::bookEntityToBookUiDto)
                .toList();
    }

    @Override
    public BookDetailsUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.getDetails(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Книга с id = %d не найдена".formatted(bookId)
                ));
        return bookMapper.bookDetailsEntityToBookDetailsDto(entity);
    }

    @Override
    public void delete(int bookId) {
        BookDetailsEntity book = bookDao.getDetails(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Книга с id = %d не найдена".formatted(bookId)
                ));
        bookDao.deleteBook(book.getId());
    }
}
