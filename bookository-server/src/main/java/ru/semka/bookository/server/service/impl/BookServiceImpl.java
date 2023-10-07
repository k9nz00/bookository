package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookContentInfoEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookCriteriaDto;
import ru.semka.bookository.server.rest.dto.book.BookDetailsUiDto;
import ru.semka.bookository.server.rest.dto.book.BookRequestDto;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.transformers.Transformer;
import ru.semka.bookository.server.transformers.wrapper.BookDetailsWrapper;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCoverService bookCoverService;
    private final Transformer<BookDetailsWrapper, BookDetailsUiDto> bookDetailsTransformer;
    private final Transformer<BookEntity, BookUiDto> booksTransformer;
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public void save(BookRequestDto dto, MultipartFile book, MultipartFile cover) throws IOException {
        BookEntity bookEntity = bookDao.save(dto);
        if (Objects.nonNull(cover)) {
            bookCoverService.saveCover(bookEntity.getId(), cover);
        }
        if (Objects.nonNull(book)) {
            BookFormat type = getType(book);
            bookDao.saveBookContent(bookEntity.getId(), book, type);
        }
    }

    @Override
    public BookUiDto update(int bookId, BookRequestDto dto) {
        BookEntity entity = bookDao.update(bookId, dto);
        return booksTransformer.transform(entity);
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
        Collection<BookEntity> books = bookDao.getBooks(criteriaDto);
        return books.stream()
                .map(booksTransformer::transform)
                .toList();
    }

    @Override
    public BookDetailsUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.find(bookId);
        Collection<BookContentInfoEntity> contentInfo = bookDao.getContentInfo(bookId);
        return bookDetailsTransformer.transform(new BookDetailsWrapper(entity, contentInfo));
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
