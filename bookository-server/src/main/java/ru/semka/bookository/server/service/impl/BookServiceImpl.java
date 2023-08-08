package ru.semka.bookository.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.semka.bookository.server.common.enums.BookFormat;
import ru.semka.bookository.server.dao.BookDao;
import ru.semka.bookository.server.dao.entity.BookContentInfoEntity;
import ru.semka.bookository.server.dao.entity.BookDetailsEntity;
import ru.semka.bookository.server.dao.entity.BookEntity;
import ru.semka.bookository.server.rest.dto.book.BookUiDto;
import ru.semka.bookository.server.rest.dto.book.CreateBookRequestDto;
import ru.semka.bookository.server.service.BookCoverService;
import ru.semka.bookository.server.service.BookService;
import ru.semka.bookository.server.transformers.Transformer;
import ru.semka.bookository.server.transformers.wrapper.BookDetailsWrapper;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final BookCoverService bookCoverService;
    private final Transformer<BookDetailsWrapper, BookUiDto> bookDetailsTransformer;
    private final Base64.Encoder encoder = Base64.getEncoder();

    @Override
    public void save(CreateBookRequestDto dto, MultipartFile book, MultipartFile bookCover) throws IOException {
        BookEntity bookEntity = bookDao.save(dto);
        bookCoverService.saveCover(bookEntity.getId(), bookCover);

        BookFormat type = getType(book);
        bookDao.saveBookContent(bookEntity.getId(), book, type);
    }

    @Override
    public BookUiDto getDetails(int bookId) {
        BookDetailsEntity entity = bookDao.find(bookId);
        Collection<BookContentInfoEntity> contentInfo = bookDao.getContentInfo(bookId);
        return bookDetailsTransformer.transform(new BookDetailsWrapper(entity, contentInfo));
    }

    @Override
    public String getBookContent(int bookId, int bookContentId) {
        return encoder.encodeToString(bookDao.getBookContent(bookId, bookContentId));
    }

    private BookFormat getType(final MultipartFile book) {
        String filename = book.getResource().getFilename();
        int formatIndex = filename.lastIndexOf(".");
        String formatValue = filename.substring(formatIndex + 1);
        return BookFormat.valueOf(formatValue.toUpperCase());

    }
}
