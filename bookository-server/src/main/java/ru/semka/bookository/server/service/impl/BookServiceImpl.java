package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.common.exception.ResourceNotFoundException;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.dao.entity.BookWithSmallPreviewEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.transformers.Transformer;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCoverService bookCoverService;
    private final Transformer<BookDetailsEntity, BookDetailsUiDto> bookDetailsTransformer;
    private final Transformer<BookWithSmallPreviewEntity, BookUiDto> bookTransformer;
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public void save(BookRequestDto dto, MultipartFile book, MultipartFile cover) throws IOException {
        BookEntity bookEntity = bookDao.save(dto);
        if (Objects.nonNull(book)) {
            BookFormat type = getType(book);
            bookDao.saveBookContent(bookEntity.getId(), book, type);
        }
        if (Objects.nonNull(cover)) {
            bookCoverService.saveCover(bookEntity.getId(), cover);
        }
    }

    @Override
    public BookUiDto update(int bookId, BookRequestDto dto) {
        // TODO need implemented
        //BookEntity entity = bookDao.update(bookId, dto);
        //return bookTransformer.transform(entity);
        return null;
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
    public Collection<BookUiDto> getBooks(BookCriteriaDto criteriaDto) {
        Collection<BookWithSmallPreviewEntity> books = bookDao.getBooks(criteriaDto);
        return books.stream()
                .map(bookTransformer::transform)
                .toList();
    }

    @Override
    public BookDetailsUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.getDetails(bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Book with id = %d not found", bookId)
                ));
        return bookDetailsTransformer.transform(entity);
    }

    @Override
    public String getBookContent(int bookId, int bookContentId) {
        return encoder.encodeToString(bookDao.getBookContent(bookId, bookContentId));
    }

    @Override
    public void updateBookCover(int bookId, MultipartFile cover) throws IOException {
        bookCoverService.saveCover(bookId, cover);
    }

    @Override
    public void deleteBookCover(int bookId) {
        bookCoverService.deleteCover(bookId);
    }

    private BookFormat getType(final MultipartFile book) {
        String filename = book.getResource().getFilename();
        int formatIndex = filename.lastIndexOf(".");
        String formatValue = filename.substring(formatIndex + 1);
        return BookFormat.fromValue(formatValue.toUpperCase());
    }
}
