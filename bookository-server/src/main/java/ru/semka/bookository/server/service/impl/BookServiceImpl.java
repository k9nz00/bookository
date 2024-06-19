package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.PredicateProvider;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.factory.CriteriaPredicateFactory;
import ru.semka.bookository.server.mapper.BookMapper;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.util.FileUtil;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCoverService bookCoverService;
    private final CriteriaPredicateFactory<BookCriteriaDto, BookWithSmallPreviewEntity> bookCriteriaPredicateFactory;
    private final BookMapper bookMapper;
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public void save(BookRequestDto dto) {
        bookDao.save(dto);
    }

    @Override
    public Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto) {
        PredicateProvider<BookWithSmallPreviewEntity> predicateProvider = bookCriteriaPredicateFactory.create(criteriaDto);
        Collection<BookWithSmallPreviewEntity> books = bookDao.getBooks(criteriaDto, predicateProvider);
        return books.stream()
                .map(bookMapper::bookWithSmallPreviewToBookUiDto)
                .toList();
    }

    @Override
    public BookUiDto update(int bookId, BookRequestDto dto) {
        BookWithSmallPreviewEntity entity = bookDao.update(bookId, dto);
        return bookMapper.bookWithSmallPreviewToBookUiDto(entity);
    }

    @Override
    public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public void deleteBookContent(int bookId, int bookContentId) {
        bookDao.deleteBookContent(bookId, bookContentId);
    }

    @Override
    public void attachBook(int bookId, MultipartFile book) throws IOException {
        BookFormat type = getType(book);
        bookDao.saveBookContent(bookId, book, type);
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
    public String getBookContent(int bookId, int bookContentId) {
        Optional<byte[]> bookContent = bookDao.getBookContent(bookId, bookContentId);
        return bookContent.map(encoder::encodeToString)
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

    private BookFormat getType(final MultipartFile book) {
        String fileFormat = FileUtil.getFileFormat(book);
        return BookFormat.fromValue(fileFormat.toUpperCase());
    }
}
