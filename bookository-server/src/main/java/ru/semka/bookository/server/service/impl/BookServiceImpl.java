package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookContentDao;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookContentEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.mapper.BookMapper;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.util.FileUtil;
import ru.semka.bookository.server.util.ResponseUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookContentDao bookContentDao;
    private final BookCoverService bookCoverService;
    private final CriteriaPredicateFactory<BookCriteriaDto, BookEntity> bookCriteriaPredicateFactory;
    private final BookMapper bookMapper;

    @Override
    public BookUiDto save(BookRequestDto dto) {
        return bookMapper.bookEntityToBookUiDto(bookDao.save(dto));
    }

    @Override
    public Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto) {
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
    public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public void deleteBookContent( int bookContentId) {
        bookContentDao.deleteById(bookContentId);
    }

    @Override
    public void attachBook(int bookId, MultipartFile book) throws IOException {
        BookContentEntity entity = new BookContentEntity();
        entity.setBookId(bookId);
        entity.setName(book.getName());
        entity.setSize(book.getSize());
        entity.setBookFormat(getFormat(book));
        entity.setContent(book.getBytes());

        bookContentDao.save(entity);
    }

    @Override
    public BookDetailsUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.getDetails(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Book with id = %d not found", bookId)
                ));
        return bookMapper.bookDetailsEntityToBookDetailsDto(entity);
    }

    @Override
    public ResponseEntity<Resource> getBookContent(int bookId, int bookContentId) {
        Optional<BookContentEntity> byIdAndAndBookId = bookContentDao.findByIdAndAndBookId(bookContentId, bookId);
        return byIdAndAndBookId
                .map(entity -> {
                    Resource resource = new ByteArrayResource(entity.getContent());
                    return ResponseEntity.ok()
                            .headers(ResponseUtil.getHeaders(entity.getName()))
                            .body(resource);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found book content for book with id = %d and content id = %d".formatted(bookId, bookContentId)
                ));
    }

    @Override
    public void updateBookCover(int bookId, MultipartFile cover) throws IOException {
        bookCoverService.deleteCover(bookId);
        bookCoverService.saveCover(bookId, cover);
    }

    @Override
    public void deleteBookCover(int bookId) {
        bookCoverService.deleteCover(bookId);
    }

    private BookFormat getFormat(final MultipartFile book) {
        String fileFormat = FileUtil.getFileFormat(book);
        return BookFormat.fromValue(fileFormat.toUpperCase());
    }
}
